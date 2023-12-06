package io.nuptse.pasal

import grails.plugin.springsecurity.userdetails.GrailsUser
import org.springframework.security.core.GrantedAuthority

/**
 *
 * @author Niraj Pokharel
 * @email niraj.pokharel @t gmail.com
 * @Date 01/11/2019
 * @Copyright © Nuptse Technologies, 2019
 * All rights reserved
 *
 * @ref https://github.com/grails-coder/grails-spring-security
 *
 */

class AuthManagerBean extends GrailsUser{
  final String fullName

  AuthManagerBean(String username,
                  String password,
                  boolean enabled,
                  boolean accountNonExpired,
                  boolean credentialsNonExpired,
                  boolean accountNonLocked,
                  Collection<GrantedAuthority> authorities,
                  long id,
                  String fullName){
    super(username,
            password,
            enabled,
            accountNonExpired,
            credentialsNonExpired,
            accountNonLocked,
            authorities,
            id)
    this.fullName = fullName
  }

}
