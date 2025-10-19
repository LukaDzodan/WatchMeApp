package com.example.watchmeapp.data.remote.network

import com.example.watchmeapp.common.Resource
import com.example.watchmeapp.data.remote.dto.movie_details_dto1.MovieDetailsDto
import com.example.watchmeapp.data.remote.dto.movie_from_query_dto.MovieFromQeuryResponse
import com.example.watchmeapp.data.remote.dto.tranding_movies_dto.TradingMoviesResponse

interface RemoteMovieDataSource {

    suspend fun getTradingMovies(
        page : Int,
    ) : Resource<TradingMoviesResponse>

    suspend fun getMovieFromQuery(
        page: Int,
        query : String,
    ) : Resource<MovieFromQeuryResponse>

    suspend fun getMovieDetails(
        movieId : Int,
    ) : Resource<MovieDetailsDto>
}