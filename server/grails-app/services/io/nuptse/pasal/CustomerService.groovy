package io.nuptse.pasal

import grails.gorm.transactions.Transactional

@Transactional
class CustomerService {

  def queryCustomer(def params, def client){
    def customers = Customer.createCriteria().list() {
      if(params.id){
        eq 'id', Long.parseLong(params.id)
      }
      if(params.name){
        ilike 'name', '%' + params.name + '%'
      }
      if(params.phone){
        eq 'phone', params.phone
      }
      if(params.description){
        ilike 'description', '%' + params.description+'%'
      }
      if(params.address){
        ilike 'address', '%' + params.address+'%'
      }
      if(params.email){
        ilike 'email', '%' + params.email+'%'
      }
      eq 'client', client
      eq 'isDeleted', false
    }
    return customers
  }

  def saveCustomer(def json, def client) {
    Customer customer
    if(json.containsKey("key")){
      customer = Customer.findById(json.key)
    } else {
      customer = new Customer()
    }
    customer.name = json.name
    customer.email = json.email
    customer.phone = json.phone
    customer.client = client
    if(json.description)  customer.description = json.description
    if(json.address ) customer.address = json.address
    customer.save(flush:true, failonError:true)
  }

  def delete(def id) {
    log.info("id ${id}")
    def customer = Customer.findById(id)
    customer.isDeleted = true
    customer.save(failOnError:true)
  }

  /**
   * Formatted list of vendors for drop-down display.
   * @param client
   * @return
   */
  def getCustomersList(def client) {
    def customers = Customer.findAllByClientAndIsDeleted(client, false)
    def result = []
    customers.collect{
      result << ['label': it.name, 'value': it.id]
    }
    return result
  }

  def get(def id){
    return Customer.get(id)
  }

}