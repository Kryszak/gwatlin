package com.kryszak.gwatlin.api.pvp

import com.kryszak.gwatlin.config.WiremockConfig
import spock.lang.Subject

class PvPAuthenticatedClientSpec extends WiremockConfig {

    def API_KEY = "1234"

    @Subject
    def pvpClient = new GWPvPAuthenticatedClient(API_KEY)

    def "Should get pvp stats"() {
        given: "External api is stubbed"
        stubAuthResponse("/pvp/stats", "/responses/pvp/stats.json", API_KEY)

        when: "Pvp stats are requested"
        def stats = pvpClient.getPvpStats()

        then: "Retrieved stats matches expected"
        verifyAll(stats) {
            pvpRank == 5
            pvpRankPoints == 6513
            pvpRankRollovers == 4
            aggregate != null
            verifyAll(professions) {
                elementalist != null
                guardian != null
            }
            verifyAll(ladders) {
                ranked != null
                unranked != null
            }
        }
    }

    def "Should get pvp game ids"() {
        given: "External api is stubbed"
        stubAuthResponse("/pvp/games", "/responses/pvp/account_game_ids.json", API_KEY)

        when: "Requesting list of pvp game ids"
        def gameIds = pvpClient.getPvpGameIds()

        then: "Retrieved list matches expected"
        gameIds == [
                "0639D2B5-FFD8-4D5F-9BDF-29E1A4C8A73D",
                "114BCDCA-8443-4E65-BDCD-00AA4EBA2A81",
                "1AB530B8-297E-4FA5-B7BE-BA4BAC420EC3",
                "4717EE75-1DF5-4DBA-8951-C38AE3943139",
                "590AD259-8B96-46B9-BB72-AF075FE5A4CA",
                "7E66F29E-23CF-4B06-B340-8B8717461027",
                "8AF2A34A-4A2A-45BA-A2C5-9B9155623192",
                "8B50CA43-CF66-48A6-8F00-74B4C3AB0AAE",
                "E80FB755-2901-49DC-B2CE-8ABE9B200EE9",
                "F8FA9E4E-21AB-4617-B1A4-ACAC12700F58"
        ]
    }

    def "Should get pvp game"() {
        given: "External api is stubbed"
        stubAuthResponse("/pvp/games?ids=ABCDE02B-8888-FEBA-1234-DE98765C7DEF", "/responses/pvp/game.json", API_KEY)

        when: "Requesting pvp game"
        def games = pvpClient.getPvpGames(["ABCDE02B-8888-FEBA-1234-DE98765C7DEF"])

        then: "Retrieved game matches expected"
        verifyAll(games.get(0)) {
            id == "ABCDE02B-8888-FEBA-1234-DE98765C7DEF"
            mapId == 894
            started == "2015-07-08T21:29:50.000Z"
            ended == "2015-07-08T21:37:02.000Z"
            result == "Defeat"
            team == "Red"
            profession == "Guardian"
            scores != null
            ratingType == "Ranked"
            ratingChange == -26
            season == "49CCE661-9DCC-473B-B106-666FE9942721"
        }
    }

    def "Should get pvp standings"() {
        given: "External api is stubbed"
        stubAuthResponse("/pvp/standings", "/responses/pvp/standings.json", API_KEY)

        when: "Requesting pvp standings"
        def standings = pvpClient.getPvpStanding()

        then: "Retrieved standings match expected"
        verifyAll(standings) {
            seasonId == "44B85826-B5ED-4890-8C77-82DDF9F2CF2B"
            verifyAll(current) {
                totalPoints == 101
                division == 4
                tier == 1
                points == 1
                repeats == 0
                rating == 845
                decay == 100
            }
            verifyAll(best) {
                totalPoints == 200
                division == 5
                tier == 4
                points == 0
                repeats == 2
            }
        }
    }
}
