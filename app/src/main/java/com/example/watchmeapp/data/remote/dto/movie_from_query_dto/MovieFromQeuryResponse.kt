package com.example.watchmeapp.data.remote.dto.movie_from_query_dto

import kotlinx.serialization.Serializable

@Serializable
data class MovieFromQeuryResponse(
    val page: Int,
    val results: List<MovieFromQueryDto>,
    val total_pages: Int,
    val total_results: Int
)