package io.nuptse.pasal

import grails.gorm.transactions.Transactional

class ClientService {

    def queryClient(def params){
        def clients = Client.createCriteria().list() {
            if(params.id){
                eq 'id', Long.parseLong(params.id)
            }
            if(params.name){
                ilike 'name', '%' + params.name + '%'
            }
            if(params.phone){
                eq 'phone', params.phone
            }
            if(params.description){
                ilike 'description', '%' + params.description+'%'
            }
            if(params.address){
                ilike 'address', '%' + params.address+'%'
            }
            if(params.email){
                ilike 'email', '%' + params.email+'%'
            }

           eq 'deleted', false
        }
        return clients
    }
    @Transactional
    def saveClient(def json, def client) {
        log.info "createClient json = $json"
//        log.info("vat" + json.isVat)
        Client client1
        if(json.containsKey("key")){
            client1 = Client.findById(json.key)
        } else {
            client1= new Client()
        }
        client1.name = json.name
        client1.email = json.email
        client1.code = json.code
        client1.url = json.url
        client1.displayName= json.displayName
        client1.vat = json.vat
        client1.pan = json.pan
        client1.address =json.address
       client1.phone = json.phone

            //vat
      if (json.isVat) {
          client1.isVat = json.isVat
      } else {
          client1.isVat = false
      }
            //inventory
        if (json.hasInventory) {
            client1.hasInventory = json.hasInventory
        } else {
            client1.hasInventory = false
        }
            //customer
        if (json.hasCustomer) {
            client1.hasCustomer = json.hasCustomer
        } else {
            client1.hasCustomer = false
        }

            //vendor
        if (json.hasVendor) {
            client1.hasVendor = json.hasVendor
        } else {
            client1.hasVendor = false
        }


//        client1.client = client
        if(json.description)  client1.description = json.description
        return client1.save(flush:true, failonError:true)
    }

    

    def delete(def id) {
        log.info("id ${id}")
        def client = Client.findById(id)
        client.deleted = true
        client.save(flush:true, failOnError:true)
    }

    /**
     * Formatted list of vendors for drop-down display.
     * @param client
     * @return
     */
//    def getClientsList(def name) {
//        def client1 = Client.findAllByNameAndDeleted(name, false)
//        def result = []
//        client1.collect{
//            result << ['label': it.name, 'value': it.id]
//        }
//        return result
//    }
    def getClientsList(def client) {
        def clients = Client.findAll()
        def result = []
        clients.collect{
            result << ['label': it.name, 'value': it.id]
        }
        log.info("result is ${result}")
        return result
    }

    def get(def id){
        def client
        try {
            client = Client.get(id)
        }catch (Exception e) {
            e.printStackTrace()
        }
        return client;
    }

}