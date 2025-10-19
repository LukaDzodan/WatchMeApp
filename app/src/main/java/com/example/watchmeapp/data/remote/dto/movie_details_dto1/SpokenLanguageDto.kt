package com.example.watchmeapp.data.remote.dto.movie_details_dto1

import kotlinx.serialization.Serializable

@Serializable
data class SpokenLanguageDto(
    val english_name: String?,
    val iso_639_1: String?,
    val name: String?
)