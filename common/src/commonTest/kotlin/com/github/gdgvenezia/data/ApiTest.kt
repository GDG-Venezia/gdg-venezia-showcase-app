package com.github.gdgvenezia.data
import runTest
import kotlin.test.assertEquals

class ApiTest {

    private var api = Api()

    @kotlin.test.Test
    fun getPhotos() = runTest {
        val response = api.getPhotos()
        assertEquals(3, response.size)
    }

    /*
    @kotlin.test.Test
    fun getPhotosJson() = runTest {
        val response = api.getPhotosJson()
        assertEquals("", response)
    }
    */
}