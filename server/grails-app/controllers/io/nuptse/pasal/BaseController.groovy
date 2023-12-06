package io.nuptse.pasal

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN', 'ROLE_USER','ROLE_SUPER_ADMIN'])
class BaseController {
  def userService
  def springSecurityService
  def dashboardService
  def fakeDataService

  def currentUser(){
    // log.info("current user hit")
    def currentUser = userService.currentUser(springSecurityService.principal.username)
    render currentUser as JSON , contentType: "application/json"
  }

  def analysis(){
    def json = [:]
    json ['introduceRowData'] = [
      'totalProducts' : dashboardService.getTotalProductsCount(params.client),
      'totalCustomers' : dashboardService.getTotalCustomersCount(params.client),
      'totalSalesAmount' : dashboardService.getTotalSalesAmount(params.client).toString()
    ]
    json ['salesData'] = dashboardService.getSalesData(params.client)
    json ['allSalesData'] =  dashboardService.getAllSales(params.client)
    json ['salesRankData'] = dashboardService.getSalesRankList(params.client)
    json ['offlineData'] = [:]
    json ['success'] = true
    //json ['totalExpensesAmount'] = decimalFormat.format(dashboardService.getTotalExpensesAmount(params.client))
    log.info("json ${json}")

    render json as JSON, contentType: "application/json"
  }

  def fakeAnalysisData() {
    def result = [
            success: true,
            data: fakeDataService.serviceMethod(params)
    ]
    render result as JSON
  }

}
