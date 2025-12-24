package com.example.watchmeapp.data.remote.dto.movie_details_dto

import kotlinx.serialization.Serializable

@Serializable
data class CreditsDto(
    val cast: List<CastDto>,
    val crew: List<CrewDto>
)