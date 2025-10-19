package com.example.watchmeapp.data.mappers

import com.example.watchmeapp.data.remote.dto.tranding_movies_dto.TradingMovieDto
import com.example.watchmeapp.domain.model.movie.Movie

interface TrendingMovieMapper {
    fun map(
        trendingmovie: TradingMovieDto,
    ): Movie

}

class TrendingMovieMappersImpl() : TrendingMovieMapper {

    override fun map(
        trendingmovie: TradingMovieDto,
    ): Movie {
        with(trendingmovie) {
            return Movie(
                id = id,
                poster_path = if(poster_path != null) "https://image.tmdb.org/t/p/w500$poster_path" else "",
                release_date = release_date,
                title = title,
                vote_average = vote_average
            )
        }
    }
}