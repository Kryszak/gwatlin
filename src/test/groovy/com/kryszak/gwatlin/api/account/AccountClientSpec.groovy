package com.kryszak.gwatlin.api.account

import com.kryszak.gwatlin.config.WiremockConfig
import spock.lang.Subject

class AccountClientSpec extends WiremockConfig {

    def API_KEY = "1234"

    @Subject
    def accountClient = new GWAccountClient(API_KEY)

    def "Should get account details"() {
        given: "External api is stubbed"
        stubAuthResponse("/account", "/responses/account/account.json", API_KEY)

        when: "Requesting account details"
        def account = accountClient.getAccountDetails()

        then: "Retrieved details matches expected"
        verifyAll(account) {
            id == "C19467C6-F5AD-E211-8756-78E7D1936222"
            name == 'Account.1234'
            age == 22911780
            world == 1004
            guilds.size() == 5
            guildLeader.size() == 1
            created == "2013-04-25T22:09:00Z"
            access.size() == 3
            commander
            fractalLevel == 100
            dailyAp == 7659
            monthlyAp == 1129
            wvwRank == 514
        }
    }

    def "Should get account achievements"() {
        given: "External api is stubbed"
        stubAuthResponse("/account/achievements", "/responses/account/achievements.json", API_KEY)

        when: "Requesting account achievements"
        def achievements = accountClient.getAccountAchievements()

        then: "Retrieved info matches expectes"
        verifyAll(achievements.get(2)) {
            id == 1653
            bits.size() == 4
            current == 4
            max == 30
            !done
        }
    }
}
