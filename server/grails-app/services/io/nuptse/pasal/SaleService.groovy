package io.nuptse.pasal


import grails.gorm.transactions.Transactional

@Transactional
class SaleService {

  def getSales(def client) {

    return Sale.findAllByClientAndIsVoid(client, false, [sort : 'billDate', order : 'desc'])
  }

  def querySales(def params ) {
    // eq 'billDate', Constants.SALE_DATE_FORMAT.parse(date1)
    log.info("params " + params)
    def sales = Sale.createCriteria().list() {
      log.info("Sales params is " + params.billDate)
      if(params.id){
        eq 'id', Long.parseLong(params.id)
      }

      if(params.customer){
        customer {
          ilike 'name', '%'+params.customer+'%'
        }
      }
      if(params.billDate) {
//        // String date = params.billDate
//        // String date1 = date.substring(1, 20)
      eq 'billDate' , io.nuptse.pasal.utils.Constants.SALE_DATE_FORMAT.parse(params.billDate)
      }
      if(params.remarks){
          ilike 'remarks', '%'+params.remarks+'%'
      }
      if(params.total){
        eq 'total', Double.parseDouble(params.total)
      }
      if(params.isCredit){
        if(params.isCredit == "true" || params.isCredit == "false"){
          eq 'isCredit', Boolean.parseBoolean(params.isCredit)
        }
      }

      eq 'client', params.client
      eq 'isVoid', false
      order('billDate', 'desc')
    }
    println "Rendering ${sales.properties}"
    return sales
  }

  def getSale(def client, def id) {
    return Sale.findByIdAndClient(id, client)
  }

  def updateSale(Sale sale, def json) {

     log.info ("Sale before ${sale.properties}")
    if(json.containsKey("partialAmount")) {
      Payment payment = new Payment()
      payment.amount = Double.parseDouble( json.partialAmount )
      payment.billDate = new Date()
      payment.save(failOnError:true)
      println "payment = $payment.properties"
      sale.addToPayments(payment)
      def totalPartialPayment = 0
      for (Payment p : sale.payments){
        log.info("p ${p.properties}")
        totalPartialPayment += p.amount
      }
      log.info("totalPartialPayment ${totalPartialPayment}")
      if(totalPartialPayment == sale.grossAmount() ) {
        sale.isCredit=false
      }
      println "totalPartialPayment = ${totalPartialPayment}"
    }else {
      log.info("not not not")
    }

    /*if(json.containsKey("discountAmount")) {
      // TODO: change discount logic and add check [ discount > total]
      sale.discount = Double.parseDouble( json.discountAmount )
    }*/
    log.info ("Sale after ${sale.properties}")
    if(sale.save()){
      return true
    }
    return false
  }

  def saveSale(def json, def client) {
    log.info('in save sale ??? ')
    Sale sale = new Sale()
    sale.client = client
    sale.billDate = io.nuptse.pasal.utils.Constants.SALE_DATE_FORMAT.parse(json.billDate)
    log.info('sale obj now ' + sale.properties)
    if(json.get("customer") != 0) {
      sale.customer = Customer.get(json.customer)
    }
    if(json.has("remarks")) {
      sale.remarks = json.get("remarks")
    }
    if(json.has("discount")) {
      sale.discount = json.get("discount")
    }
    sale.subTotal = json.subTotal
    sale.grandTotal = json.grandTotal
    sale.isCredit = json.get("isCredit")
    def items = json.get("items")

    items.each {
      LineItem lineItem = new LineItem()
      lineItem.product = io.nuptse.pasal.product.Product.get(it.product)
      lineItem.unitPrice = it.price
      lineItem.quantity = it.quantity
      lineItem.subTotal = it.total
      lineItem.particulars = it.particulars
      lineItem.save(flush:true, failOnError:true)
      sale.addToLineItems(lineItem)
    }
    double sum = 0
    sale.lineItems.each {
      sum += it?.subTotal
    }
    sale.total = sum
    log.info ("sum ${sum}")

    if (!sale.isCredit) {
      Payment payment = new Payment(billDate: sale.billDate, amount: sale.total)
      payment.save(flush: true, failOnError: true)
      sale.addToPayments(payment)
    }
    log.info "Sale inside service ${sale.properties}"
    // sale.save(flush : true, failOnError : true)
    try{
      sale.save()
    } catch (Exception e) {
      e.printStackTrace()
    }

    /*if (!sale.save(failOnError: true)) {
      sale.errors.each {
        println it
      }
    }*/
  }
}
