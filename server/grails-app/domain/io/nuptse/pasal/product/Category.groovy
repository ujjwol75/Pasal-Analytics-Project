package io.nuptse.pasal.product

import io.nuptse.pasal.Client

class Category {
    String name
    String description
    Integer sortOrder = 0
    Category parentCategory
    Client client
    Date dateCreated
    Date lastUpdated
    Boolean isDeleted = false
    Boolean isRoot = false

    static hasMany = [categories: Category]
    static mappedBy = [categories: "parentCategory"]
    static belongsTo = [parentCategory: Category]
    static transients = ["parents", "children", "deleted", "products"]

    static mapping = {
        // id generator: 'uuid'
        sort name: "desc"
        categories sort: "name", cascade: "all-delete-orphan"
        cache true
    }

    static constraints = {
        name(nullable: false, maxSize: 255)
        description(nullable: true, maxSize: 255)
        sortOrder(nullable: true)
        isRoot(nullable: true)
        isDeleted(nullable: true)
        client (nullable: true)
        // parent category can't be the category itself or any of its children
        parentCategory(
            nullable: true,
            validator: { value, obj ->
                value != obj && !(obj.getChildren().find {
                    it == value
                })
            }
        )
    }

    /**
     *
     * @return
     */
    def getChildren() {
        return categories ? categories*.children.flatten() + categories : []
    }

}
