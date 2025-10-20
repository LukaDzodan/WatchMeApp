package com.example.watchmeapp.presentation.movie_details_screen

sealed interface MovieDetailsActions {
    object AddToFavouriteClick : MovieDetailsActions
    object DeleteFromFavouriteClick : MovieDetailsActions
    object OnBackStack : MovieDetailsActions
}