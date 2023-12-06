package io.nuptse.pasal

class UrlMappings {

    static mappings = {
        delete "/$controller/$id(.$format)?"(action:"delete")
        get "/$controller(.$format)?"(action:"index")
        get "/$controller/$id(.$format)?"(action:"show")
        post "/$controller(.$format)?"(action:"save")
        put "/$controller/$id(.$format)?"(action:"update")
        patch "/$controller/$id(.$format)?"(action:"patch")

        "/api/fake_analysis_chart_data" controller: 'base', action: 'fakeAnalysisData'

        "/api/client"   (resources: "client")
        "/api/category" (resources: "category")
        "/api/user"   (resources: "user")
        "/api/currentUser" controller: 'base', 'action':'currentUser'
        "/api/analysis" controller: 'base', 'action':'analysis'
        "/api/customer"   (resources: "customer")
        "/api/product"   (resources: "product")
        "/api/sale"   (resources: "sale")
        "/api/vendor"   (resources: "vendor")
        "/api/user/list" controller: 'user', action: 'listUsers'
        "/api/customer/list" controller: 'customer', action: 'list'
        "/api/client/list" controller: 'client', action: 'listClients'
        // "/api/client/list/$id" controller: 'client', action: 'listclient'
        "/api/product/list" controller: 'product', action: 'listProducts'
        "/api/category/list" controller: 'category', action: 'listCategories'
        "/api/product/price/$id" controller: 'product', action: 'price'
        "/api/vendor/list" controller: 'vendor', action: 'listVendors'
        "/api/role/list" controller: 'user', action: 'listRoles'
        "/"(controller: 'application', action:'index')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
