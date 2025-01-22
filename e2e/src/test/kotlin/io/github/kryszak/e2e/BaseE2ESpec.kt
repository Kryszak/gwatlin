package io.github.kryszak.e2e

import io.kotest.core.spec.style.ExpectSpec
import io.kotest.core.test.TestCaseOrder

open class BaseE2ESpec : ExpectSpec() {
    override fun testCaseOrder(): TestCaseOrder = TestCaseOrder.Sequential
}