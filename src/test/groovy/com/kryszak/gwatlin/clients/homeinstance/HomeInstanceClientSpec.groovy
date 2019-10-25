package com.kryszak.gwatlin.clients.homeinstance

import com.google.common.reflect.TypeToken
import com.kryszak.gwatlin.api.achievement.model.exception.ApiRequestException
import com.kryszak.gwatlin.api.homeinstance.model.Cat
import spock.lang.Subject

class HomeInstanceClientSpec extends HomeInstanceStubs {

    @Subject
    def homeInstanceClient = new HomeInstanceClient()

    def "Should get cat ids list"() {
        given: "Expected id list"
        def catIds = parseResponse("/responses/homeinstance/cat_ids.json")

        and: "External api is stubbed"
        stubCatIdsResponse()

        when: "Cat ids list is requested"
        def catIdsList = homeInstanceClient.getCatIds()

        then: "Retrieved list matches expected list"
        catIdsList == catIds
    }

    def "Should get cat"() {
        given: "Cat id"
        def id = 1

        and: "External api is stubbed"
        stubCatResponse()

        when: "Cat is requested"
        def cat = homeInstanceClient.getCat(id)

        then: "Retrieved cat matches expected"
        cat == parseCat()
    }

    def "Should throw exception on non existing cat"() {
        given: "Non existing cat id"
        def id = 100

        and: "External api is stubbed"
        stubErrorCatResponse()

        when: "Cat is requested"
        homeInstanceClient.getCat(id)

        then: "Retrieved cat matches expected"
        thrown(ApiRequestException)
    }

    def "Should get cat list"() {
        given: "Cat ids"
        def ids = [1, 2, 3]

        and: "External api is stubbed"
        stubCatsResponse()

        when: "Cats are requested"
        def catList = homeInstanceClient.getCats(ids)

        then: "Retrieved list matches expected"
        catList == parseCats()
    }

    def "Should get node ids list"() {
        given: "Expected node ids"
        def nodeIds = parseResponse("/responses/homeinstance/node_ids.json")

        and: "External api is stubbed"
        stubNodeIdsResponse()

        when: "Node ids list is requested"
        def nodeIdsList = homeInstanceClient.getNodesIds()

        then: "Retrieved list matches expected"
        nodeIdsList == nodeIds
    }

    private Cat parseCat() {
        gson.fromJson(parseResponseText("/responses/homeinstance/cat.json"), Cat)
    }

    private List<Cat> parseCats() {
        gson.fromJson(parseResponseText("/responses/homeinstance/cats.json"),
                new TypeToken<List<Cat>>() {}.getType())
    }
}
