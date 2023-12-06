package io.nuptse.pasal.product

import grails.converters.JSON
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT

@Secured(['ROLE_ADMIN', 'ROLE_USER','ROLE_SUPER_ADMIN'])
class ProductController {

  def baseService
  def productService

  static responseFormats = ['json', 'xml']
  static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

  def index(Integer max) {
    respond productService.queryProduct(params)
  }

  def show(Long id) {
    respond productService.get(id)
  }

  def price(Long id){
    respond productService.getPrice(id)
  }

  @Transactional
  def save(){
    def json = request.getJSON()
    // def client = baseService.getClientFromRequest(request)
    boolean result = productService.saveProduct(json, params.client)
    def data
    if(result){
      data = [success: true]
    }else{
      data = [success: true]
    }
    render data as JSON
  }

  @Transactional
  def update() {
    def json = request.getJSON()
    boolean result = productService.saveProduct( json, params.client)
    if(result){
      render {["success": true]}
    }else{
      render {["success": false]}
    }
  }

  @Transactional
  def delete(Long id) {
    if (id == null) {
      render status: NOT_FOUND
      return
    }
    productService.delete(id)
    render status: NO_CONTENT
  }

  def listProducts() {
    def products = productService.getProductsList(params.client)
    render ([success: true, data: products] as JSON, contentType: "application/json")
  }
}
