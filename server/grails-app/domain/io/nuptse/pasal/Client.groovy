package io.nuptse.pasal

class Client {
    String code
    String url
    String name
    String email
    String displayName
    String phone
    String vat
    String pan
    String address
    String description
    // client module indicators
    boolean isVat
    boolean hasVendor
    boolean hasCustomer
    boolean hasInventory
    boolean deleted = false
    boolean enabled = true
    Date dateCreated
    Date lastUpdated
    static constraints = {
        displayName nullable: true, blank: true
        email nullable: true, blank: true
        description nullable: true, blank: true
        name nullable: true, blank: true
        url nullable: true, blank: true
        code nullable: true, blank: true
        phone nullable: true, blank: true
        vat nullable: true, blank: true
        pan nullable: true, blank: true
        address nullable: true, blank: true
        enabled nullable: true, blank: true
    }
    
}
