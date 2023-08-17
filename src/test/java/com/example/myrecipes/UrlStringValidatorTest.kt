package com.example.myrecipes

import junitparams.JUnitParamsRunner
import junitparams.Parameters
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(JUnitParamsRunner::class)
class UrlStringValidatorTest {

    lateinit var subject: UrlStringValidator

    @Before
    fun setUp() {
        subject = UrlStringValidator()
    }

    @Parameters(
        "google.com",
        "google.com ",
        " google.com",
        "GOOGLE.COM"
    )
    @Test
    fun processUrlTest(
        url: String
    ) {
        assertEquals(EXPECTED_URL, subject.processUrl("google.com"))
    }

    private companion object {
        const val EXPECTED_URL = "https://google.com"
    }
}