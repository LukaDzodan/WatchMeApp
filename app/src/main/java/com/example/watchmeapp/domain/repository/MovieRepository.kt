package com.example.watchmeapp.domain.repository

import com.example.watchmeapp.common.Resource
import com.example.watchmeapp.domain.model.movie.Movie
import com.example.watchmeapp.domain.model.movie_details.MovieDetails
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getTradingMovies(
        page : Int,
    ) : Resource<List<Movie>>

    suspend fun getMovieFromQuery(
        page: Int,
        query : String
    ) : Resource<List<Movie>>

    suspend fun getMovieDetails(
        movieId : Int,
    ) : Resource<MovieDetails>

    suspend fun getFavoritesMovies() : Flow<List<Movie>>

    suspend fun insertFavoriteMovie(movie : Movie)

    suspend fun deleteFavoriteMovie(movie : Movie)

}