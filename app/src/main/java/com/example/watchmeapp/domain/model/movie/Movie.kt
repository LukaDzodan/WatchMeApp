package com.example.watchmeapp.domain.model.movie

data class Movie(
    val id: Int,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double,
)