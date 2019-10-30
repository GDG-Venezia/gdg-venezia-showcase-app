package com.github.gdgvenezia.domain.entities

/**
 * @author Andrea Maglie
 */
data class PhotoModel(
        val title: String,
        val url: String,
        val tags: List<String>
)