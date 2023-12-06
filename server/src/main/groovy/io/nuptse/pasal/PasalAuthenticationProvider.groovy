package io.nuptse.pasal

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

import javax.servlet.http.HttpServletRequest

class PasalAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private AuthManagerService authManagerService

    @Autowired
    private HttpServletRequest request


    @Override
    Authentication authenticate(Authentication authentication) throws AuthenticationException {

        //println "CustomAuthenticationProvider::authenticate -> Called"
        //println "CustomAuthenticationProvider::authentication -> ${authentication?.dump()}"
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder()

        String header = request.getHeader("Host")
        String client = header.substring(0, header.indexOf("."))

        String username = authentication.getName()
        String password = authentication.getCredentials().toString()

        UserDetails user = authManagerService.loadUserByUsername(username)
        //println "CustomAuthenticationProvider::user obtained -> ${user.dump()}"

        if(!user || !user.username.equalsIgnoreCase(username)){
            throw new BadCredentialsException("Username not found.")
        } else {
            //println "Username validation passed."
        }

        if(!passwordEncoder.matches(password, user.password.replace("{bcrypt}","").trim())){
            println "Checking password ... wrong pwd ${password} "
            throw new BadCredentialsException("Wrong password.")
        } else {
            //println "Password validation passed."
        }

        if(!authManagerService.checkUserClient(username, client)){
            println "Client validation failed for user ${username}  client:->  ${client} "
            throw new BadCredentialsException("User not associated with client")
        }else {
            // println "Client validation passed."
        }

        //println "CustomAuthenticationProvider::authenticate -> Passed"

        Collection<? extends GrantedAuthority> authorities = user.getAuthorities()

        //println "CustomAuthenticationProvider::authorities are -> ${authorities.dump()}"

        return new UsernamePasswordAuthenticationToken(user, password, authorities)

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class)
    }

}
