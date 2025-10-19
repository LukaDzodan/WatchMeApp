package com.example.watchmeapp.data.remote.dto.tranding_movies_dto

import kotlinx.serialization.Serializable

@Serializable
data class TradingMoviesResponse(
    val page: Int,
    val results: List<TradingMovieDto>,
    val total_pages: Int,
    val total_results: Int
)