package io.nuptse.pasal

import grails.converters.JSON
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT

@Secured(['ROLE_ADMIN', 'ROLE_USER','ROLE_SUPER_ADMIN'])
class CustomerController {
  static responseFormats = ['json', 'xml']

  def baseService
  def customerService

  def index() {
    log.info("Customers params" + params)
    def client = baseService.getClientFromRequest(request)
    def list = customerService.queryCustomer(params, client)
    def result = [success: true, data:list]
    render result as JSON
  }

  def show(Long id) {
    respond customerService.get(id)
  }

  @Transactional
  def save(){
    boolean result = customerService.saveCustomer(request.getJSON(), params.client)
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
    boolean result = customerService.saveCustomer( json, params.client)
    if(result){
      render {["success": true]}
    }else{
      render {["success": false]}
    }
  }

  /**
   * Build a list of customer name and id for customer dropdowns
   * @return
   */
  def list() {
    //def client = baseService.getClientFromRequest(request)
    // def data = customerService.getCustomersList(params.client)
    def customers = customerService.getCustomersList(params.client)
    // respond customers
    render ([success: true, data: customers] as JSON, contentType: "application/json")
  }

  @Transactional
  def delete(Long id) {
    if (id == null) {
      render status: NOT_FOUND
      return
    }
    customerService.delete(id)
    def result = [success: true]
    render result as JSON
  }

}
