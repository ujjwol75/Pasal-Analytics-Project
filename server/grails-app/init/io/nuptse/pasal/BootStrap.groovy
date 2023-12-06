package io.nuptse.pasal

import grails.util.Environment

class BootStrap {
    def baseService
    def init = { servletContext ->
        println "    ____                   __   ___                __      __  _          \n" +
                "   / __ \\____ __________ _/ /  /   |  ____  ____ _/ /_  __/ /_(_)_________\n" +
                "  / /_/ / __ `/ ___/ __ `/ /  / /| | / __ \\/ __ `/ / / / / __/ / ___/ ___/\n" +
                " / ____/ /_/ (__  ) /_/ / /  / ___ |/ / / / /_/ / / /_/ / /_/ / /__(__  ) \n" +
                "/_/    \\__,_/____/\\__,_/_/  /_/  |_/_/ /_/\\__,_/_/\\__, /\\__/_/\\___/____/  \n" +
                "                                                 /____/                "
        baseService.createAuthorities()
        if(Environment.current == Environment.DEVELOPMENT){
            baseService.createUserAndClient()
            baseService.assignUserRoles()
            baseService.createCategories()
            baseService.createVendors()
        }
    }
    def destroy = {
    }
}
