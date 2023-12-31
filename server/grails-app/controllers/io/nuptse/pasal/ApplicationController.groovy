package io.nuptse.pasal

import grails.converters.JSON
import grails.core.GrailsApplication
import grails.plugin.springsecurity.annotation.Secured
import grails.plugins.*

@Secured(['permitAll'])
class ApplicationController implements PluginManagerAware {

    GrailsApplication grailsApplication
    GrailsPluginManager pluginManager
    def fakeDataService

    def index() {
        [grailsApplication: grailsApplication, pluginManager: pluginManager]
    }
}
