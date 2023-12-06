package io.nuptse.pasal

class Payment {
  Double amount
  Date billDate
  Date dateCreated
  Date lastUpdated
  static constraints = {
    amount nullable: false, blank : false
  }
}
