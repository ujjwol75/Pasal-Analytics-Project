package io.nuptse.pasal.product

import grails.gorm.transactions.Transactional

@Transactional
class CategoryService {

    def queryCategory (def params) {
        def categories = Category.createCriteria().list() {
            if (params.id)
                eq 'id', Long.parseLong(params.id)
            if(params.name){
                ilike 'name', '%'+params.name+'%'
            }
            eq 'client', params.client
            eq 'isDeleted', false
            order 'dateCreated', 'desc'
        }
        return categories
    }

    /**
     * Formatted list of categories for drop-down display.
     * @param client
     * @return
     */
    def getCategoryList(def client) {
        def categories = Category.findAllByClientAndIsDeleted(client, false)
        def result = []
        categories.collect{
            result << ['label': it.name, 'value': it.id]
        }
        return result
    }

    def saveCategory( def json, def params ) {
        Category category
        if(json.containsKey("key")) {
            category = Category.findById(json.key)
        } else {
            category = new Category()
        }
        category.client = params.client
        category.properties = json
        try {
            category.save(failOnError:true)
        }catch (Exception e) {
            e.printStackTrace()
        }


       /* if (!category.save(failOnError: true, flush:true)) {
            log.info("Error exist")
            category.errors.allErrors.each {
                println it
            }
            return false
        } else {
            log.info("Saved category")
            return true
        }*/

    }

    def get(def id){
        return Category.get(id)
    }

    @javax.transaction.Transactional
    def delete(def id) {
        try {
            log.info("id ${id}")
            def category = Category.findById(id)
            category.isDeleted = true
            category.save(flush:true, failOnError:true)
        }catch (Exception e) {
            e.printStackTrace()
        }
    }

}
