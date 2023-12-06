package io.nuptse.pasal

import io.nuptse.pasal.product.Product

class LineItem {
  Product product
  int quantity
  double unitPrice
  double subTotal
  String particulars

  Date dateCreated
  Date lastUpdated

  static constraints = {
    product nullable: true,blank:true
    quantity nullable: true,blank:true
    unitPrice nullable: true,blank:true
    particulars nullable: true,blank:true
    subTotal nullable:false, blank:false
  }
}
