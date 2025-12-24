package com.example.watchmeapp.common

sealed class Screens(val route : String) {
    object MovieListScreen : Screens("movie_list_screen")
    object MovieDetailsScreen : Screens("movie_details_screen")
}