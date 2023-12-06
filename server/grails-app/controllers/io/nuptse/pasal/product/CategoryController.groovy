package io.nuptse.pasal.product

import grails.converters.*
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.annotation.Secured
import groovy.util.logging.Slf4j

import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT

@Slf4j
@Secured(['ROLE_ADMIN', 'ROLE_USER','ROLE_SUPER_ADMIN'])
class CategoryController {
	// static responseFormats = ['json', 'xml']
	CategoryService categoryService

    def index(Integer max) {
        log.info("params ==> " + params)
        params.max = max
        respond categoryService.queryCategory(params)
    }

    def show(Long id) {
        respond categoryService.get(id)
    }

    @Transactional
    def save() {
        log.info("save" + request.getJSON())
        def json = request.getJSON()
        boolean result = categoryService.saveCategory(json, params)
        log.info("service result " + result )
        def data
        if (result) {
            data = [success: true]
        } else {
            data = [success: false]
        }
        render data as JSON
    }

    def listCategories() {
        def categories = categoryService.getCategoryList(params.client)
        render ([success: true, data: categories] as JSON, contentType: "application/json")
    }

    @Transactional
    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }
        categoryService.delete(id)
        render status: NO_CONTENT
    }
}
