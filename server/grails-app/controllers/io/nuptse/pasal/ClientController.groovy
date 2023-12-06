package io.nuptse.pasal

import grails.converters.JSON
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT

@Secured(['ROLE_ADMIN', 'ROLE_USER','ROLE_SUPER_ADMIN'])
class ClientController {
    static responseFormats = ['json', 'xml']

    def baseService
    def clientService

    def index() {
        log.info("Client params" + params)
        def patron = baseService.getClientFromRequest(request)
        respond clientService.queryClient(params)
    }

    def show(Long id) {
        respond clientService.get(id)
    }

    def save() {
        boolean result = clientService.saveClient(request.getJSON(), params.client)
        if (result) {
            render { ["success": true] }
        } else {
            render { ["success": false] }
        }
    }

//    def listClients() {
//
//        //def client = baseService.getClientFromRequest(request)
//        def result = clientService.getClientList()
//        log.info("result" + result)
//        render (text: result as JSON, contentType: "application/json")
//    }

    @Transactional
    def update() {
        def json = request.getJSON()
        boolean result = clientService.saveClient(request.getJSON(), params.client)
        if(result){
            render {["success": true]}
        }else{
            render {["success": false]}
        }
    }

    @Transactional
    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }
        clientService.delete(id)
        render status: NO_CONTENT
    }

    def listClients() {
        def clients = clientService.getClientsList(params.client)
        render ([success: true, data: clients] as JSON, contentType: "application/json")
    }


}