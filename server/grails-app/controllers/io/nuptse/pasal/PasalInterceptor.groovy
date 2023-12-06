package io.nuptse.pasal


class PasalInterceptor {
    def baseService

    PasalInterceptor() {
        match(controller: 'base|customer|product|sale|vendor|category', action: '*')
    }

    boolean before() {
        Client client = baseService.getClientFromRequest(request)
        params.client = client
        true
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
