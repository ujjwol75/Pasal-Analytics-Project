package io.nuptse.pasal


import grails.converters.JSON
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.annotation.Secured
import io.nuptse.pasal.auth.User

import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT

@Secured(['ROLE_ADMIN', 'ROLE_USER','ROLE_SUPER_ADMIN'])
class UserController {

    def baseService
    def userService


    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index() {
        def  list = userService.queryuser(params)
        respond list
    }

    def show(Long id) {
        respond userService.get(id)
    }

    @Transactional
    def save(){
        def json = request.getJSON()
        /*def result = userService.saveuser(json, params.user)
        println result*/
        try {
            userService.saveUser(json)
        } catch (Exception e) {
            render {["success": false]} as JSON
        }
        render {["success": true]} as JSON

        /*boolean saveResult = userService.saveuser(json, params.client)
        def data

        if(saveResult){
            println("LOL")
            data = {["success": true]}
        }else{
            println("NIEN")
            data = {["success": false]}
        }*/

        // render data as JSON
    }

    @Transactional
    def update() {
        def id = request.getJSON()['id']
        User user = User.findById(id)
        println user.properties
        def json = request.getJSON()
        json.each {k, v->
            println "k = $k v= $v"
            user[k] = v
        }
        if(user.save(failOnError:true)){
            render {["success": true] as JSON}
        }else{
            render {["success": false] as JSON}
        }
    }
    @Transactional
    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }
        userService.delete(id)
        render status: NO_CONTENT
    }

    def listUsers() {
        def result = userService.getUsersList()
        render (text: result as JSON, contentType: "application/json")
    }

    def listRoles() {
        def result = userService.getRolesList()
        render (text: result as JSON, contentType: "application/json")
    }
}
