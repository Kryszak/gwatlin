package com.kryszak.gwatlin.http.config

import spock.lang.Specification
import spock.lang.Subject

class HttpConfigSpec extends Specification {

    @Subject
    def httpConfig = new HttpConfig()

    def "Should load http config"() {
        expect: "Valid url parsed from config"
        httpConfig.baseUrl == "http://localhost:8089"
    }
}
