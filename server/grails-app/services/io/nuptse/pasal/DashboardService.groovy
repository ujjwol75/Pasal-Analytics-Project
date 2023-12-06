package io.nuptse.pasal

import grails.gorm.transactions.Transactional

import java.text.SimpleDateFormat

@Transactional
class DashboardService {

  def getTotalProductsCount(def client) {
    return io.nuptse.pasal.product.Product.findAllByClient(client).size()
  }

  def getTotalCustomersCount(def client) {
    return Customer.findAllByClient(client).size()
  }

  def getTotalSalesAmount(def client) {
    def total = Sale.createCriteria().get {
      eq 'client', client
      projections {
        eq 'isVoid', false
        sum('total')
      }
    }
    return total ?: 0
  }

  def getSalesData(def client) {
    def salesData = []
    def s = Sale.createCriteria()
    def results = s.list {
      projections {
        sum('total')
        groupProperty('billDate')
      }
      order('billDate')
    }
    results.each{
      def sdf = new SimpleDateFormat("YYYY-MM-dd").format(it.getAt(1))
      salesData << [ "x": sdf ,  y: it.getAt(0)]
    }
    return salesData
  }

  def getAllSales(def client) {
    def results = Sale.createCriteria().list {
      eq 'client', client
      eq 'isVoid', false
    }
    return results
  }

  def getSalesRankList(def client){
    def sales = Sale.findAllByClientAndIsVoid(client, false, [max: 7, sort: 'total', order:'desc'])
    def rankData = []
    sales.each {
      rankData << ['title': it?.customer?.name?:"Un-Customer" , 'total': it?.total, 'id': it?.id]
    }
    //log.info "Sales rankData ${rankData}"
    return rankData
  }
}
