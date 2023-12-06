package io.nuptse.pasal.product

import grails.gorm.services.Service
import io.nuptse.pasal.Vendor
import io.nuptse.pasal.auth.User

import javax.transaction.Transactional
import java.text.SimpleDateFormat

@Service(Product)
class ProductService {
  def springSecurityService
  def queryProduct (def params) {
    def products = Product.createCriteria().list() {
      if(params.id){
        eq 'id', Long.parseLong(params.id)
      }
      if(params.name){
        ilike 'name', '%'+params.name+'%'
      }
      if(params.price){
        eq 'price', Double.parseDouble( params.price )
      }
      if(params.description){
        ilike 'description', '%'+params.description+'%'
      }
      if(params.vendor){
        vendor {
          ilike 'name', '%'+params.vendor+'%'
        }
      }
      if(params.category) {
        category {
          ilike 'name', '%'+params.category+'%'
        }
      }
      if(params.lastUpdated) {
        Date lastUpdated = new SimpleDateFormat('yyyy-MM-dd').parse(params.lastUpdated)
        Calendar calendar = Calendar.getInstance()
        calendar.setTime(lastUpdated)
        calendar.add(Calendar.DATE, 1)
        Date currentDatePlusOne = calendar.getTime()
        between( 'lastUpdated',lastUpdated, currentDatePlusOne)
      }

      eq 'client', params.client
      eq 'isDeleted', false
      order 'dateCreated', 'desc'
    }
    //println "Rendering ${products?.size()} Accounts of ${products?.class}"
    return products
  }

  boolean saveProduct(def json, def client) {
    try{
      log.info("The json product save " + json)
      Product product
      if(json.containsKey("key")){
        product = Product.findById(json.key)
      } else {
        product = new Product()
      }
      product.name = json.name
      product.pricePerUnit = json.pricePerUnit
      product.description = json?.description
      product.client = client
      product.category = Category.get(json.category)
      product.createdBy =  User.get( springSecurityService.currentUserId )
      product.lastUpdatedBy = User.get( springSecurityService.currentUserId )
      def vendor = json.vendor
      if( vendor instanceof String ){
        product.vendor = Vendor.findByName(json.vendor)
      }else{
        product.vendor = Vendor.get(json.vendor)
      }
      product.save(failOnError:true)
    }catch (Exception e) {
      e.printStackTrace()
      return false
    }
  }

  /**
   * Formatted list of products for drop-down display.
   * @param client
   * @return
   */
  def getProductsList(def client) {
    def products = Product.findAllByClientAndIsDeleted(client, false)
    def result = []
    products.collect{
      result << ['label': it.name, 'value': it.id]
    }
    return result
  }

  def get(def id){
    return Product.get(id)
  }

  def getPrice(def id){
    return Product.get(id)
  }

  @Transactional
  def delete(def id) {
    log.info("id ${id}")
    def product = Product.findById(id)
    product.isDeleted = true
    product.save(flush:true, failOnError:true)
  }
}