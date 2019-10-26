package com.github.gdgvenezia.coroutines.web

import kotlinext.js.require
import react.dom.render
import kotlin.browser.document
import kotlin.browser.window

fun main() {
    /*
    val globalState = object {
        val networkRepository = makeRepository()
    }*/

    require("main.css")

    window.onload = {
        render(document.getElementById("content")) {
            app {
                attrs {
                    //networkRepository = globalState.networkRepository
                }
            }
        }
    }
}

/*
private fun makeRepository(): NetworkRepository =
    NetworkRepository(
        dataSource = NetworkDataSource()
    )
*/