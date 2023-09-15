package com.kryszak.gwatlin.api.homeinstance

import com.google.common.reflect.TypeToken
import com.kryszak.gwatlin.api.exception.ApiRequestException
import com.kryszak.gwatlin.api.homeinstance.model.Cat
import com.kryszak.gwatlin.config.WiremockTest
import kotlinx.serialization.SerializersKt
import kotlinx.serialization.builtins.BuiltinSerializersKt
import spock.lang.Subject

class HomeInstanceClientSpec extends WiremockTest {

    @Subject
    def homeInstanceClient = new GWHomeInstanceClient()

    def "Should get cat ids list"() {
        given: "Expected id list"
        def catIds = parseResponse("/responses/homeinstance/cat_ids.json")

        and: "External api is stubbed"
        stubResponse("/home/cats", "/responses/homeinstance/cat_ids.json")

        when: "Cat ids list is requested"
        def catIdsList = homeInstanceClient.getCatIds()

        then: "Retrieved list matches expected list"
        catIdsList == catIds
    }

    def "Should get cat"() {
        given: "Cat id"
        def id = 1

        and: "External api is stubbed"
        stubResponse("/home/cats/1", "/responses/homeinstance/cat.json")

        when: "Cat is requested"
        def cat = homeInstanceClient.getCat(id)

        then: "Retrieved cat matches expected"
        cat == parseCat()
        verifyAll(cat) {
            hint == "chicken"
            id == 1
        }
    }

    def "Should throw exception on non existing cat"() {
        given: "Non existing cat id"
        def id = 100

        and: "External api is stubbed"
        stubNotFoundResponse("/home/cats/100", "/responses/homeinstance/cat_error.json")

        when: "Cat is requested"
        homeInstanceClient.getCat(id)

        then: "Retrieved cat matches expected"
        thrown(ApiRequestException)
    }

    def "Should get cat list"() {
        given: "Cat ids"
        def ids = [1, 2, 3]

        and: "External api is stubbed"
        stubResponse("/home/cats?ids=1,2,3", "/responses/homeinstance/cats.json")

        when: "Cats are requested"
        def catList = homeInstanceClient.getCats(ids)

        then: "Retrieved list matches expected"
        catList == parseCats()
    }

    def "Should get node ids list"() {
        given: "Expected node ids"
        def nodeIds = parseResponse("/responses/homeinstance/node_ids.json")

        and: "External api is stubbed"
        stubResponse("/home/nodes", "/responses/homeinstance/node_ids.json")

        when: "Node ids list is requested"
        def nodeIdsList = homeInstanceClient.getNodeIds()

        then: "Retrieved list matches expected"
        nodeIdsList == nodeIds
    }

    private Cat parseCat() {
        json.decodeFromString(SerializersKt.serializer(Cat), parseResponseText("/responses/homeinstance/cat.json")) as Cat
    }

    private List<Cat> parseCats() {
        json.decodeFromString(BuiltinSerializersKt.ListSerializer(SerializersKt.serializer(Cat)), parseResponseText("/responses/homeinstance/cats.json")) as List<Cat>
    }
}
