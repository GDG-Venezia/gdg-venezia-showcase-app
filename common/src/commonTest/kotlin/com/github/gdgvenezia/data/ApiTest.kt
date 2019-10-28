package com.github.gdgvenezia.data
import runTest
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class ApiTest {

    private var api = Api()

    @kotlin.test.Test
    fun getPhotos() = runTest {
        val response = api.getPhotos()
        assertEquals(3, response.size)
    }

    @kotlin.test.Test
    fun getSocialLinks() = runTest {
        val response = api.getSocialLinks()
        assertEquals(8, response.size)

        assertNotNull(response.find { it.code == "facebook" })
        assertNotNull(response.find { it.code == "twitter" })
        assertNotNull(response.find { it.code == "youtube" })
        assertNotNull(response.find { it.code == "meetup" })
        assertNotNull(response.find { it.code == "instagram" })
        assertNotNull(response.find { it.code == "github" })
        assertNotNull(response.find { it.code == "telegram" })
        assertNotNull(response.find { it.code == "mail" })
    }

    /*
    @kotlin.test.Test
    fun getPhotosJson() = runTest {
        val response = api.getPhotosJson()
        assertEquals("", response)
    }
    */
}