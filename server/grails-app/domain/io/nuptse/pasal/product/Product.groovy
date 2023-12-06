package io.nuptse.pasal.product

import io.nuptse.pasal.Client
import io.nuptse.pasal.Vendor
import io.nuptse.pasal.auth.AuthService
import io.nuptse.pasal.auth.User

class Product {
  // transient springSecurityService
  /*def beforeInsert = {
    // User.withNewSession {
      def currentUser = springSecurityService.currentUserId // springSecurityService.authentication.getPrincipal()
      if (currentUser) {
        createdBy = currentUser
        lastUpdatedBy = currentUser
      }else {
        log.info("no current user")
      }
    // }
  }
  def beforeUpdate = {
    User.withNewSession {
      def currentUser = AuthService.currentUser.get()
      if (currentUser) {
        lastUpdatedBy = currentUser
      }
    }
  }*/

  // Specific description for the product
  String name

  // Details product description
  String description

  // Internal product code identifier (or SKU)
  // http://en.wikipedia.org/wiki/Stock_keeping_unit
  String productCode

  // Type of product (good, service, fixed asset)
  ProductType productType

  // Price per unit (global for the entire system)
  BigDecimal pricePerUnit

  // Cost per unit
  BigDecimal costPerUnit

  // Indicates whether the product is active.
  // we do not want to delete the product because it may be referenced in existing transactions
  Boolean active = Boolean.TRUE

  // primary category
  Category category

  // (e.g. each, tablet, pill, bottle, box)
  String unitOfMeasure

  // Universal product code - http://en.wikipedia.org/wiki/Universal_Product_Code
  String upc

  // Custom product attributes
  // List attributes = new ArrayList()

  // List of product components - bill of materials
  // List productComponents

  boolean isDeleted = false

  Vendor vendor
  Client client
  Date dateCreated
  Date lastUpdated
  User createdBy
  User lastUpdatedBy

  /*static transients = [
          // "rootCategory", "categoriesList", "images"
  ]*/

  static hasMany = [
          categories         : Category,
          attributes         : ProductAttribute,
  ]

  static constraints = {
    name (blank:false, nullable: false, maxSize: 255)
    description (blank: true, nullable: true)
    productCode (nullable: true, maxSize: 255, unique: true)
    productType nullable: true
    pricePerUnit nullable: true
    costPerUnit nullable: true
    active nullable: true
    category nullable: false
    unitOfMeasure (nullable: true, maxSize: 255)
    upc nullable: true
    createdBy nullable: true
    lastUpdatedBy nullable: true
    vendor (nullable: true, blank:true)
  }
}
