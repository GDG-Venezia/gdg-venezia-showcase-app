package com.github.gdgvenezia.showcase

import org.junit.*
import org.junit.runner.*
import androidx.test.runner.*
import androidx.fragment.app.testing.*

/**
 * https://developer.android.com/training/basics/fragments/testing
 */
@RunWith(AndroidJUnit4::class)
class EventsFragmentTest {

    @org.junit.Test
    fun lunchFragment() {
        val scenario = launchFragment<EventsFragment>()
        assertNotNull(scenario)
    }
}