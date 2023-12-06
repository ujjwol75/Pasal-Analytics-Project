package io.nuptse.pasal.product

class ProductType {
    String name
    // ProductTypeCode productTypeCode

    String code
    String productIdentifierFormat
    Integer sequenceNumber = 0

    Date dateCreated
    Date lastUpdated
    static constraints = {
        name(blank: false)
        code(nullable: true, unique: true)
       //  productTypeCode(nullable: false)
        productIdentifierFormat(nullable: true)
        sequenceNumber(nullable: true)
    }
}
