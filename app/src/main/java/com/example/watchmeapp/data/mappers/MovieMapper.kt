package com.example.watchmeapp.data.mappers

import com.example.watchmeapp.data.local.MovieEntity
import com.example.watchmeapp.data.remote.dto.movie_from_query_dto.MovieFromQueryDto
import com.example.watchmeapp.domain.model.movie.Movie

interface MovieMapper {

    fun map(
        movie : MovieFromQueryDto
    ) : Movie

    fun map(
        movie : MovieEntity
    ) : Movie


}

class MovieMappersImpl() : MovieMapper {

    override fun map(movie: MovieFromQueryDto) : Movie{
        return Movie(
            id = movie.id,
            poster_path = "https://image.tmdb.org/t/p/w500${movie.poster_path}",
            release_date = movie.release_date,
            title = movie.title,
            vote_average = movie.vote_average
        )
    }

    override fun map(movie: MovieEntity): Movie {
        return Movie(
            id = movie.id,
            poster_path = "https://image.tmdb.org/t/p/w500${movie.poster_path}",
            release_date = movie.release_date,
            title = movie.title,
            vote_average = movie.vote_average
        )
    }


}