package com.kryszak.gwatlin.achievements

import com.kryszak.gwatlin.config.WiremockConfig
import spock.lang.Subject

class AchievementsClientSpec extends WiremockConfig {

    @Subject
    def achievementsClient = new AchievementsClient()

    def "Should get list of achievement ids"() {
        given: "Expected achievement id list"
        def achievementIds = parseResponse("/responses/achievements/achievement_ids.json")

        and: "External api is stubbed"
        stubAchievementIdsResponse()

        when: "Achievement list is retrieved"
        def idsList = achievementsClient.getAchievementIdsList()

        then: "Retrieved list matches expected list"
        idsList == achievementIds
    }
}
