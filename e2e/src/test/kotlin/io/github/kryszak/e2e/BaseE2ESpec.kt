package io.github.kryszak.e2e

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.core.test.TestCaseOrder

internal open class BaseE2ESpec : ExpectSpec() {
    override fun testCaseOrder(): TestCaseOrder = TestCaseOrder.Sequential

    fun testedApiLanguages(): List<ApiLanguage> = listOf(ApiLanguage.EN)
}