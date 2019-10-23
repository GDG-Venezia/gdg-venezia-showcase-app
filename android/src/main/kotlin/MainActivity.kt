package org.konan.multiplatform

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.github.gdgvenezia.ServiceLocator
import com.github.gdgvenezia.domain.entities.EventListModel
import com.github.gdgvenezia.presentation.events.EventListView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var eventsFragment: EventsFragment
    private lateinit var aboutFragment: AboutFragment
    private lateinit var photoFragment: PhotoFragment
    private lateinit var contactsFragment: ContactsFragment
    private lateinit var currentFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        eventsFragment = EventsFragment()
        currentFragment = eventsFragment
        setFragment(eventsFragment, EVENT_TAG)

        val navigation = findViewById<BottomNavigationView>(R.id.HOME_bottom_nav)

        navigation.selectedItemId = R.id.menu_events
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        when (item.itemId) {
            R.id.menu_events -> {
                if (::eventsFragment.isInitialized) {
                    hideAndShow(eventsFragment, currentFragment)
                } else {
                    eventsFragment = EventsFragment()
                    hideAndAdd(eventsFragment, currentFragment, EVENT_TAG)
                }
                currentFragment = eventsFragment
                return@OnNavigationItemSelectedListener true
            }
            R.id.menu_team -> {
                if (::aboutFragment.isInitialized) {
                    hideAndShow(aboutFragment, currentFragment)
                } else {
                    aboutFragment = AboutFragment()
                    hideAndAdd(aboutFragment, currentFragment, ABOUT_TAG)
                }
                currentFragment = aboutFragment
                return@OnNavigationItemSelectedListener true
            }
            R.id.menu_photo -> {
                if (::photoFragment.isInitialized) {
                    hideAndShow(photoFragment, currentFragment)
                } else {
                    photoFragment = PhotoFragment()
                    hideAndAdd(photoFragment, currentFragment, PHOTO_TAG)
                }
                currentFragment = photoFragment
                return@OnNavigationItemSelectedListener true
            }
            R.id.menu_contacts -> {
                if (::contactsFragment.isInitialized) {
                    hideAndShow(contactsFragment, currentFragment)
                } else {
                    contactsFragment = ContactsFragment()
                    hideAndAdd(contactsFragment, currentFragment, CONTACT_TAG)
                }
                currentFragment = contactsFragment
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    /**
     * Sets a new one Fragment. Called when there isn't a previous fragment
     */
    private fun setFragment(fragment: Fragment, TAG: String) {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.replace(R.id.MAIN_frame, fragment, TAG)
        ft.commit()
    }

    /**
     * Shows a fragment that is already in the stack
     */
    private fun hideAndShow(toShow: Fragment, toHide: Fragment) {
        toHide.onHiddenChanged(true)
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.hide(toHide)
        ft.show(toShow)
        ft.commit()
        toShow.onHiddenChanged(false)
    }


    /**
     * Add a new Fragment. Called when there is a previous fragment
     * and the new one isn't in stack
     */
    private fun hideAndAdd(toAdd: Fragment, toHide: Fragment, toShowTAG: String) {
        toHide.onHiddenChanged(true)
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.hide(toHide)
        ft.add(R.id.MAIN_frame, toAdd, toShowTAG)
        ft.commit()
        toAdd.onHiddenChanged(false)
    }

    companion object {
        const val EVENT_TAG = "EVENT_FRAGMENT"
        const val ABOUT_TAG = "ABOUT_FRAGMENT"
        const val PHOTO_TAG = "PHOTO_FRAGMENT"
        const val CONTACT_TAG = "CONTACT_FRAGMENT"
    }
}