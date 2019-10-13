package com.kryszak

import kotlin.test.assertEquals
import org.junit.Test

class GwatlinTest {

    @Test
    fun testHello() {
        val gwatlin = Gwatlin()
        assertEquals("Hello!", gwatlin.hello())
    }

}