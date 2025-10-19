package com.example.watchmeapp.presentation.movie_details_screen

import com.example.watchmeapp.domain.model.movie_details.Credits
import com.example.watchmeapp.domain.model.movie_details.MovieDetails

data class MovieDetailsState(
    val movieDetails : MovieDetails = movieDetailsFake,
    val isLoading : Boolean = false,
    val errorMessage : String = "",
    val isMovieSaved : Boolean = false
)

val movieDetailsFake = MovieDetails(
    backdrop_path = "",
    budget = 0,
    credits = Credits(emptyList()),
    genres = emptyList(),
    homepage = "",
    id = 0,
    imdb_id = "",
    origin_country = emptyList(),
    original_language = "",
    original_title = "",
    overview = "",
    poster_path = "",
    production_companies = emptyList(),
    release_date = "",
    revenue = 0,
    runtime = 0,
    status = "",
    title = "",
    vote_average = 0.0
)