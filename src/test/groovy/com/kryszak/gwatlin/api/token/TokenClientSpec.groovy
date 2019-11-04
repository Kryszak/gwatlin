package com.kryszak.gwatlin.api.token

import com.kryszak.gwatlin.config.WiremockConfig

class TokenClientSpec extends WiremockConfig {

    def API_KEY = "1234"

    def tokenClient = new GWTokenClient(API_KEY)

    def "Should get token info"() {
        given: "External api is stubbed"
        stubAuthResponse("/tokeninfo", "/responses/token/token.json", API_KEY)

        when: "Requesting token info"
        def tokenInfo = tokenClient.getTokenInfo()

        then: "Retrieved info matches expected"
        verifyAll(tokenInfo) {
            id == "ABCDE02B-8888-FEBA-1234-DE98765C7DEF"
            name == "My API Key"
            permissions == [
                    "account",
                    "characters",
                    "tradingpost",
                    "unlocks",
                    "build"
            ]
        }
    }
}
