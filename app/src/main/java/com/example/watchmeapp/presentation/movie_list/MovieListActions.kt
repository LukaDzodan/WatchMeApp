package com.example.watchmeapp.presentation.movie_list

import com.example.watchmeapp.domain.model.movie.Movie

sealed interface MovieListActions {
    object LoadMoreMovies : MovieListActions
    data class OnSearchQueryChange(val query : String) : MovieListActions
    data class OnMovieClick(val movie : Movie, val isSaved : Boolean) : MovieListActions
    data class OnTabSelected(val index : Int) : MovieListActions
}

//dodaj za ucitavanje jos filmova