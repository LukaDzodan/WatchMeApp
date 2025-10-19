package com.example.watchmeapp.data.remote.dto.movie_details_dto1

import kotlinx.serialization.Serializable

@Serializable
data class ProductionCompanyDto(
    val id: Int,
    val logo_path: String?,
    val name: String,
    val origin_country: String?
)