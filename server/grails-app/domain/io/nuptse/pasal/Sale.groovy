package io.nuptse.pasal

class Sale {
  static hasMany = [lineItems: LineItem, payments : Payment]
  Customer customer
  Vendor vendor
  Client client
  Double total
  Double partialAmount
  Double discount
  Double subTotal
  Double grandTotal
  String remarks
  boolean isVoid = false
  boolean isCredit
  Date billDate
  Date dateCreated
  Date lastUpdated

  def totalPartialPayment(){
    def totalPayment = 0.0
    if(payments!=null){
      for(Payment payment: payments){
        totalPayment += payment.amount
      }
    }
    return totalPayment
  }

  def duesAmount() {
    Double dues = 0
    if(isCredit && (double) totalPartialPayment() > 0.0 ) {
      dues = total - (double)totalPartialPayment()
    }
    if( isCredit && (double) totalPartialPayment() <= 0.0) {
      dues = total
    }
    if(discount && isCredit ){
      return dues - discount
    }
    log.info "the dues ${dues}"
    return dues
  }

  Double grossAmount() {
    if(discount){
      return total - discount
    }
    return 0.0
  }

  static constraints = {
    customer nullable: true, blank : true
    vendor nullable: true, blank : true
    discount nullable: true, blank : true
    partialAmount nullable: true, blank : true
    subTotal nullable: true, blank : true
    grandTotal nullable: true, blank : true
    remarks nullable: true, blank : true
    billDate nullable: true, blank : true
    dateCreated nullable: true, blank : true
  }
}
