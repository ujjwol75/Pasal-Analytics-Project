package io.nuptse.pasal

import grails.converters.JSON
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.annotation.Secured
import org.grails.web.json.JSONObject

@Secured(['ROLE_ADMIN', 'ROLE_USER','ROLE_SUPER_ADMIN'])
class SaleController {
  static responseFormats = ['json', 'xml']
  SaleService saleService

  def index() {
    log.info "params ---> " +params
    def list = saleService.querySales(params)
    respond list
  }

  def show() {
    def sale = saleService.getSale(params.client, params.id)
    respond sale
  }

  def update(Sale sale) {
    def json = request.JSON
    log.info " the sale object ${sale.properties}"
    JSONObject requestObject = (JSONObject) request.JSON
    log.info "the update params ${params} --> $requestObject"
    boolean result =  saleService.updateSale(sale, requestObject)
    def data
    if(result){
      data = [success: true]
    }else{
      data = [success: true]
    }
    render data as JSON
  }

  @Transactional
  def save() {
    JSONObject requestObject = (JSONObject) request.JSON
    log.info "requestJSON ==> \n${requestObject.toString(2)}"
    boolean result = saleService.saveSale(requestObject, params.client)
    def data
    if(result){
      data = [success: true]
    }else{
      data = [success: true]
    }
    render data as JSON
  }
}
