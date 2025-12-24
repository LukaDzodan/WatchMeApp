package com.example.watchmeapp.data.remote.dto.movie_details_dto

import kotlinx.serialization.Serializable

@Serializable
data class ProductionCountryDto(
    val iso_3166_1: String?,
    val name: String?
)