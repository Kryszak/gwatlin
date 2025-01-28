package io.github.kryszak.gwatlin.http.config

import java.util.*

internal class HttpConfig {

    private val configFileName = "config.properties"

    private val baseUrlProperty = "url"

    private val readTimeoutProperty = "read.timeout"

    private val connectTimeoutProperty = "connect.timeout"

    private val properties: Properties = Properties()

    val baseUrl: String

    val readTimeout: Int

    val connectTimeout: Int

    init {
        loadProperties()
        this.baseUrl = properties.getProperty(baseUrlProperty)
        this.readTimeout = properties.getProperty(readTimeoutProperty).toInt()
        this.connectTimeout = properties.getProperty(connectTimeoutProperty).toInt()
    }

    private fun loadProperties() {
        properties.load(this.javaClass.classLoader.getResourceAsStream(configFileName))
    }
}
