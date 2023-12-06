package io.nuptse.pasal

class VendorService {

  def queryVendor(def params){
    def vendors = Vendor.createCriteria().list() {
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
      eq 'client', params.client
      eq 'isDeleted', false
    }
    return vendors
  }

  def saveVendor(def json, def client) {
    log.info "createVendor json = $json"
    Vendor vendor
    if(json.containsKey("key")){
      vendor = Vendor.findById(json.key)
    } else {
      vendor = new Vendor()
    }
    vendor.name = json.name
    vendor.phone = json.phone
    vendor.client = client
    if(json.description)  vendor.description = json.description
    if(json.address ) vendor.address = json.address
    if(json.email) vendor.email = json.email
    vendor.save(flush:true, failonError:true)
  }

  def delete(def id){
    log.info("id ${id}")
    Vendor vendor = Vendor.findById(id)
    if(!vendor.isDeleted) {
      vendor.isDeleted=true
      vendor.save(failOnError: true)
    }
  }

  /**
   * Formatted list of vendors for drop-down display.
   * @param client
   * @return
   */
  def getVendorsList(def client) {
    def vendors = Vendor.findAllByClientAndIsDeleted(client, false)
    def result = []
    vendors.collect{
      result << ['label': it.name, 'value': it.id]
    }
    return result
  }

  def get(def id){
    return Vendor.get(id)
  }

}