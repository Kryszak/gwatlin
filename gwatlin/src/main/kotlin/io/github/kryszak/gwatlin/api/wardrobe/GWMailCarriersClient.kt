package io.github.kryszak.gwatlin.api.wardrobe

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.wardrobe.model.MailCarrier
import io.github.kryszak.gwatlin.clients.wardrobe.MailCarriersClient

/**
 * Client for minis endpoints. Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/mailcarriers)
 * @see io.github.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWMailCarriersClient {

    private val mailCarriersClient = MailCarriersClient()

    /**
     * Returns ids of all available mail carriers
     */
    fun getMailCarrierIds(): List<Int> {
        return mailCarriersClient.getMailCarrierIds()
    }

    /**
     * Returns mail carrier for given id
     * @param id of mail carrier
     * @param language for response
     */
    fun getMailCarrier(id: Int, language: ApiLanguage? = null): MailCarrier {
        return mailCarriersClient.getMailCarrier(id, language)
    }

    /**
     * Returns mail carriers for given ids
     * @param ids of mail carriers
     * @param language for response
     */
    fun getMailCarriers(ids: List<Int>, language: ApiLanguage? = null): List<MailCarrier> {
        return mailCarriersClient.getMailCarriers(ids, language)
    }
}