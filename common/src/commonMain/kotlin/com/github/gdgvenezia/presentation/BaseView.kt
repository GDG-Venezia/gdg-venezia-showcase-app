package com.github.gdgvenezia.presentation

/**
 * @author Andrea Maglie
 */
interface BaseView {

    fun renderError(errorMessage: String)

    fun renderLoading(visible: Boolean)
}