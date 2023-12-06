package io.nuptse.pasal

class Vendor {
    //static hasMany = [vendors : Product]
    String name
    String description
    Double discount
    String address
    String phone
    String email
    Client client
    boolean isDeleted = false
    Date dateCreated
    Date lastUpdated

    static constraints = {
        description nullable: true, blank: true
        discount nullable: true
        address nullable: true, blank: true
        phone nullable:true
        email nullable:true
        client nullable: true
    }
}
