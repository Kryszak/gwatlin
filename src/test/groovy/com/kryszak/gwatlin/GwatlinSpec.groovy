package com.kryszak.gwatlin

import spock.lang.Specification
import spock.lang.Subject

class GwatlinSpec extends Specification {

    @Subject
    def gwatlin = new Gwatlin()

    def "Should get 'Hello!' from method call"() {
        when: "hello() method is called"
        def actual = gwatlin.hello()

        then: "Returned value is equal to expected"
        actual == "Hello!"
    }
}
