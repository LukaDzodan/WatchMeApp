package com.example.watchmeapp.data.remote.dto.movie_details_dto

import kotlinx.serialization.Serializable

@Serializable
data class GenreDto(
    val id: Int,
    val name: String
)