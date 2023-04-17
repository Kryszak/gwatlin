package com.kryszak.gwatlin.api.pvp

import com.kryszak.gwatlin.api.ApiLanguage
import com.kryszak.gwatlin.api.pvp.model.rank.PvpRank
import com.kryszak.gwatlin.api.pvp.model.season.PvpSeason
import com.kryszak.gwatlin.config.WiremockTest
import kotlinx.serialization.SerializersKt
import kotlinx.serialization.builtins.BuiltinSerializersKt
import spock.lang.Subject

class PvPClientSpec extends WiremockTest {

    @Subject
    def pvpClient = new GWPvPClient()

    def "Should get pvp rank ids"() {
        given: "Expected rank ids"
        def ids = parseResponse("/responses/pvp/rank_ids,json")

        and: "External api is stubbed"
        stubResponse("/pvp/ranks", "/responses/pvp/rank_ids,json")

        when: "Requesting rank ids"
        def rankIds = pvpClient.getPvpRankIds()

        then: "Retrieved list matches expected"
        rankIds == ids
    }

    def "Should get pvp ranks"() {
        given: "Pvp rank ids"
        def ids = [1, 2]

        and: "language"
        def lang = ApiLanguage.EN

        and: "External api is stubbed"
        stubResponseWithLanguage("/pvp/ranks?ids=1,2", "/responses/pvp/ranks.json", lang)

        when: "Requesting pvp ranks"
        def ranks = pvpClient.getPvpRanks(ids, lang)

        then: "Retrieved list matches expected"
        ranks == parseRanks()
        verifyAll(ranks.get(0)) {
            id == 1
            finisherId == 1
            name == "Rabbit"
            icon == "https://render.guildwars2.com/file/592A4144FE1B6904CD0C69230840B8C21A0C36F7/347222.png"
            minRank == 1
            maxRank == 9
            verifyAll(levels.get(0)) {
                minRank == 1
                maxRank == 5
                points == 500
            }
        }
    }

    def "Should get pvp season ids"() {
        given: "Expected season ids"
        def ids = parseResponse("/responses/pvp/season_ids.json")

        and: "External api is stubbed"
        stubResponse("/pvp/seasons", "/responses/pvp/season_ids.json")

        when: "Requesting season ids"
        def seasonIds = pvpClient.getPvpSeasonIds()

        then: "Retrieved list matches expected"
        seasonIds == ids
    }

    def "Should get pvp seasons"() {
        given: "Season ids"
        def ids = ["44B85826-B5ED-4890-8C77-82DDF9F2CF2B", "95D5B290-798A-421E-A919-1C2A75F74B72"]

        and: "language"
        def lang = ApiLanguage.EN

        and: "External api is stubbed"
        stubResponseWithLanguage("/pvp/seasons?ids=44B85826-B5ED-4890-8C77-82DDF9F2CF2B,95D5B290-798A-421E-A919-1C2A75F74B72",
                "/responses/pvp/seasons.json", lang)

        when: "Requesting pvp seasons"
        def seasons = pvpClient.getPvpSeasons(ids, lang)

        then: "Retrieved list matches expected"
        seasons == parseSeasons()
        verifyAll(seasons.get(0)) {
            id == "44B85826-B5ED-4890-8C77-82DDF9F2CF2B"
            name == "PvP League Season One"
            start == "2015-12-01T20:00:00.000Z"
            end == "2016-01-28T01:00:00.000Z"
            !active
            verifyAll(divisions.get(0)) {
                name == "Division 1: Amber"
                flags.size() == 0
                largeIcon == "https://render.guildwars2.com/file/02ED75461164551455297DA4955862552C2452BE/1313334.png"
                smallIcon == "https://render.guildwars2.com/file/6357FE56301B2F4AD1F309E62739B0110DA2452A/1313340.png"
                pipIcon == "https://render.guildwars2.com/file/47BDF237FF800552EDD69D28BC926031FC4B64A9/1313346.png"
                verifyAll(tiers.get(0)) {
                    points == 5
                }
            }
            verifyAll(leaderboards.legendary) {
                verifyAll(settings) {
                    name == ""
                    duration == 0
                    scoring == "E6487336-4B5B-4BFA-9CFA-9FF232CAEF85"
                    verifyAll(tiers.get(0)) {
                        range.size() == 2
                    }
                }
                verifyAll(scorings.get(0)) {
                    id == "E6487336-4B5B-4BFA-9CFA-9FF232CAEF85"
                    type == "Integer"
                    description == "Current prestige rank. Prestige rank can be gained or lost by winning or losing ranked matches in the legendary division."
                    name == "Prestige"
                    ordering == "MoreIsBetter"
                }
            }
        }
    }

    private List<PvpSeason> parseSeasons() {
        json.decodeFromString(
                BuiltinSerializersKt.ListSerializer(SerializersKt.serializer(PvpSeason)),
                parseResponseText("/responses/pvp/seasons.json")
        ) as List<PvpSeason>
    }

    private List<PvpRank> parseRanks() {
        json.decodeFromString(
                BuiltinSerializersKt.ListSerializer(SerializersKt.serializer(PvpRank)),
                parseResponseText("/responses/pvp/ranks.json")
        ) as List<PvpRank>
    }
}
