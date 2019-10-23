package com.github.gdgvenezia.domain.entities

data class TeamMemberModel(
        val firstname: String,
        val lastname: String,
        val pictureUrl: String,
        val shortDescription: String,
        val longDescription: String,
        val twitterUrl: String,
        val linkedinUrl: String
)
