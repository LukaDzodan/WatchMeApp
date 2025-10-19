package com.example.watchmeapp.data.remote.dto.movie_details_dto1

import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailsDto(
    val adult: Boolean?,
    val backdrop_path: String?,
    val budget: Int?,
    val credits: CreditsDto?,
    val genres: List<GenreDto>?,
    val homepage: String?,
    val id: Int,
    val imdb_id: String?,
    val origin_country: List<String>?,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val production_companies: List<ProductionCompanyDto>?,
    val production_countries: List<ProductionCountryDto>?,
    val release_date: String?,
    val revenue: Int?,
    val runtime: Int?,
    val spoken_languages: List<SpokenLanguageDto>?,
    val status: String?,
    val tagline: String?,
    val title: String?,
    val video: Boolean?,
    val vote_average: Double?,
    val vote_count: Int?
)

