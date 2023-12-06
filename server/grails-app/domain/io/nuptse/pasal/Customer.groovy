package io.nuptse.pasal

class Customer {

  String name
  String description
  String phone
  String address
  String email
  boolean isDeleted = false
  Client client
  Date dateCreated
  Date lastUpdated

  static constraints = {
    description nullable: true, blank: true
    phone       nullable: true, blank: true
    address     nullable: true, blank: true
    email       nullable: true, blank: true
  }

  String toString(){
    return name
  }

}
