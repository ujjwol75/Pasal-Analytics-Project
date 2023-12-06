import io.nuptse.pasal.AuthManagerService
import io.nuptse.pasal.PasalAuthFailureHandler
import io.nuptse.pasal.PasalAuthenticationProvider
import io.nuptse.pasal.PasalRestAuthTokenJsonRenderer
import io.nuptse.pasal.UserPasswordEncoderListener

// Place your Spring DSL code here
beans = {
    userPasswordEncoderListener(UserPasswordEncoderListener)
    userDetailsService(AuthManagerService)
    pasalAuthenticationProvider(PasalAuthenticationProvider)
    restAuthenticationFailureHandler(PasalAuthFailureHandler)
    accessTokenJsonRenderer(PasalRestAuthTokenJsonRenderer)
}
