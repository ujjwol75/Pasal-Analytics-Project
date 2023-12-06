package io.nuptse.pasal.auth

import io.nuptse.pasal.Client
import grails.compiler.GrailsCompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@GrailsCompileStatic
@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class User implements Serializable {

    private static final long serialVersionUID = 1

    String username
    String password
    String avatarUrl
    String firstName
    String lastName
    String country
    String address
    String description
    String phone
    String email
    Client client
    boolean deleted = false
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    Set<Role> getAuthorities() {
        (UserRole.findAllByUser(this) as List<UserRole>)*.role as Set<Role>
    }

    static constraints = {
        password nullable: false, blank: false, password: true
        username nullable: false, blank: false, unique: true
        address blank: false, nullable: true
        country blank: false, nullable: true
        email blank: false, nullable: true
        phone blank: false, nullable: true
        firstName blank: false, nullable: true
        lastName blank: false,nullable: true
        avatarUrl blank : false , nullable: true
        description nullable: true, blank: true
    }

    def getFullName() {
        return this.firstName + " " + this.lastName
    }

    static mapping = {
	    password column: '`password`'
    }
}
