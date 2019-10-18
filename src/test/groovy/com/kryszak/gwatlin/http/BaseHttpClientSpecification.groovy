package com.kryszak.gwatlin.http

import spock.lang.Specification
import spock.lang.Subject

class BaseHttpClientSpecification extends Specification {

    @Subject
    def baseHttpClient = new BaseHttpClient()

    def "Should set up base url properly"() {
        expect: "API url is set up"
        baseHttpClient.baseUrl == "http://localhost:8089"
    }
}
