package io.nuptse.pasal.product

class ProductAttribute {
    String value
    static belongsTo = [product: Product]
    static constraints = {
        value(maxSize: 255)
    }
}
