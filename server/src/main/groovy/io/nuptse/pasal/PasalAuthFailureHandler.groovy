package io.nuptse.pasal

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AuthenticationFailureHandler

import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class PasalAuthFailureHandler implements AuthenticationFailureHandler{

  private ObjectMapper objectMapper = new ObjectMapper();
  @Override
  void onAuthenticationFailure(
    HttpServletRequest request,
    HttpServletResponse response,
    AuthenticationException exception)
    throws IOException, ServletException {

    //println("handling custom authfail", request.getParameter("type"))

    //response.setStatus(HttpStatus.UNAUTHORIZED.value());
    //response.setStatus(200)
    response.setContentType("application/json")

    Map<String, String> data = new HashMap<>()
    data.put("status", "error")
    data.put("type", "account")
    data.put("currentAuthority","guest");

    response.getOutputStream()
      .println(objectMapper.writeValueAsString(data));
  }
}
