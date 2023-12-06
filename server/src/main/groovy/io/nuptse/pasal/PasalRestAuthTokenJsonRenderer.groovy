package io.nuptse.pasal

import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.rest.token.AccessToken
import grails.plugin.springsecurity.rest.token.rendering.AccessTokenJsonRenderer
import groovy.json.JsonBuilder
import io.nuptse.pasal.auth.User
import org.springframework.security.core.GrantedAuthority

class PasalRestAuthTokenJsonRenderer implements AccessTokenJsonRenderer {

  @Override
  @Transactional (readOnly = true)
  String generateJson(AccessToken accessToken){

    // User the principal ID to get an instance of the user from the database
      User user = User.get accessToken.principal.id as Long

    def roles =  accessToken.authorities.collect { GrantedAuthority role -> role.authority }
    def currentAuthority
    if(roles.contains('ROLE_ADMIN')){
      currentAuthority = "admin"
    }else{
      currentAuthority = "user"
    }

    Map response = [
            id           : user.id,
            username     : user.username,
            access_token : accessToken.accessToken,
            is_vat       : user.client.isVat,
            has_vendor   : user.client.hasVendor,
            has_customer : user.client.hasCustomer,
            has_inventory : user.client.hasInventory,
            token_type   : "Bearer",
            refresh_token: accessToken.refreshToken,
            roles        : accessToken.authorities.collect { GrantedAuthority role -> role.authority },
            currentAuthority: currentAuthority,
            type : 'account',
            status : "ok",
            success: true
    ]

    return new JsonBuilder( response ).toPrettyString()
  }
}
