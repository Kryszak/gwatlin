package com.kryszak.gwatlin.http.config

import java.util.*

class HttpConfig {

    private val configFileName = "config.properties"

    private val baseUrlProperty = "url"

    private val properties: Properties = Properties()

    val baseUrl: String

    init {
        loadProperties()
        this.baseUrl = properties.getProperty(baseUrlProperty)
    }

    private fun loadProperties() {
        properties.load(this.javaClass.classLoader.getResourceAsStream(configFileName))
    }
}
