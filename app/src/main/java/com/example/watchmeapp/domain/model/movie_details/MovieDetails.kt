package com.example.watchmeapp.domain.model.movie_details

data class MovieDetails(
    //gotova
    val backdrop_path: String,
    //gotova
    val budget: Int,
    //gotova
    val credits: Credits,
    //gotova
    val genres: List<Genre>,
    val homepage: String,
    //gotova
    val id: Int,
    //gotova
    val imdb_id: String,
    //gotova
    val origin_country: List<String>,
    //gotova
    val original_language: String,
    //gotova
    val original_title: String,
    //gotova
    val overview: String,
    val poster_path: String,
    //gotova
    val production_companies: List<ProductionCompany>,
    //gotova
    val release_date: String,
    //gotova
    val revenue: Int,
    //gotova
    val runtime: Int,
    //gotova
    val status: String,
    //gotova
    val title: String,
    //gotova
    val vote_average: Double,
)