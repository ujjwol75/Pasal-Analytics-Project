package io.nuptse.pasal


import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.SpringSecurityUtils
import grails.plugin.springsecurity.userdetails.GrailsUserDetailsService
import grails.plugin.springsecurity.userdetails.NoStackUsernameNotFoundException
import io.nuptse.pasal.auth.Role
import io.nuptse.pasal.auth.User
import org.springframework.dao.DataAccessException
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

/**
 *
 * @author Niraj Pokharel
 * @email niraj.pokharel @t gmail.com
 * @Date 01/11/2019
 * @Copyright © Kinesis Health Technologies, 2019
 * All rights reserved
 *
 */
@Service
class AuthManagerService implements GrailsUserDetailsService{

  /**
     * Some Spring Security classes (e.g. RoleHierarchyVoter) expect at least
     * one role, so we give a user with no granted roles this one which gets
     * past that restriction but doesn't grant anything.
     */
  static final List NO_ROLES = [new SimpleGrantedAuthority(SpringSecurityUtils.NO_ROLE)]

  @Override
  UserDetails loadUserByUsername(String username, boolean loadRoles) throws UsernameNotFoundException, DataAccessException {
    //println "loadUserByUsername:: two params"
    return loadUserByUsername(username)
  }

  @Override
  @Transactional(readOnly = true, noRollbackFor = [IllegalArgumentException,UsernameNotFoundException])
  UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    //println "loadUserByUsername:: one param $username"
      User user = User.findByUsername(username)
    if (!user)
      throw new NoStackUsernameNotFoundException()

    //println "loadUserByUsername::User contents-> ${user.dump()}"

    //Set<ViewAccess> roles = user.authorities

    Set<Role> roles = user.authorities//.collect {it.authorities}.flatten().unique()
    

    def authorities = roles.collect(){
      new SimpleGrantedAuthority(it.authority)
    }

    return new AuthManagerBean(user.username,
            user.password,
            user.enabled,
            !user.accountExpired,
            !user.passwordExpired,
            !user.accountLocked,
            authorities ?: NO_ROLES, user.id,
            "Pasal User")

  }

  @Transactional (readOnly = true)
  def checkUserClient(String username, String clientname) {

    User user = User.findByUsername(username)
    Client client = Client.findByName(clientname)

    if(user?.client?.id == client?.id){
      //println "Client matched for user"
      return true
    }
    return false
  }
}