package io.nuptse.pasal

import grails.converters.JSON
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.NOT_FOUND

@Secured(['ROLE_ADMIN', 'ROLE_USER','ROLE_SUPER_ADMIN'])
class VendorController {

  VendorService vendorService
  def baseService

  static responseFormats = ['json', 'xml']
  static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

  def index() {
    log.info("customer index params" + params)
    respond vendorService.queryVendor(params)
  }

  def show(Long id) {
    respond vendorService.get(id)
  }

  @Transactional
  def save() {
    boolean result = vendorService.saveVendor(request.getJSON(), params.client)
    log.info "result ${result}"
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
    boolean result = vendorService.saveVendor( json, params.client)
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
    vendorService.delete(id)
    def result = [success: true]
    render result as JSON
  }

  def listVendors() {
    def data = vendorService.getVendorsList(params.client)
    def result = [success: true, data: data]
    render (text: result as JSON, contentType: "application/json")
  }
}
