package com.example.watchmeapp.presentation.movie_list

import com.example.watchmeapp.domain.model.movie.Movie

data class MovieListState(
    val movies: List<Movie> = emptyList(),
    val favouriteMovies : List<Movie> = emptyList(),
    val query : String = "",
    val selectedTabIndex : Int = 0,
    val isLoading : Boolean = false,
    val errorMessage : String = "",
)

//val listMovies = (1.. 100).map {
//    Movie(
//        Poster = "",
//        Title = "Title $it",
//        imdbID = it.toString(),
//    )
//}