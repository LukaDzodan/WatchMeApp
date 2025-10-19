package com.example.watchmeapp.data.mappers

import com.example.watchmeapp.data.remote.dto.movie_details_dto1.CastDto
import com.example.watchmeapp.data.remote.dto.movie_details_dto1.CreditsDto
import com.example.watchmeapp.data.remote.dto.movie_details_dto1.GenreDto
import com.example.watchmeapp.data.remote.dto.movie_details_dto1.MovieDetailsDto
import com.example.watchmeapp.data.remote.dto.movie_details_dto1.ProductionCompanyDto
import com.example.watchmeapp.domain.model.movie_details.Cast
import com.example.watchmeapp.domain.model.movie_details.Credits
import com.example.watchmeapp.domain.model.movie_details.Genre
import com.example.watchmeapp.domain.model.movie_details.MovieDetails
import com.example.watchmeapp.domain.model.movie_details.ProductionCompany

interface MovieDetailsMapper {

    fun map(
        movieDetailsDto: MovieDetailsDto
    ) : MovieDetails

}

class MovieDetailsMapperImpl() : MovieDetailsMapper {


    override fun map(movieDetailsDto: MovieDetailsDto): MovieDetails {
        with(movieDetailsDto) {
            return MovieDetails(
                backdrop_path = "https://image.tmdb.org/t/p/w500$backdrop_path",
                budget = budget ?: 0,
                credits = credits?.toCredits() ?: Credits(emptyList()),
                genres = genres?.map {
                    it.toGenre()
                } ?: emptyList(),
                homepage = homepage ?: "",
                id = id,
                imdb_id = imdb_id ?: "",
                origin_country = origin_country ?: emptyList(),
                original_language = original_language ?: "",
                original_title = original_title ?: "",
                overview = overview ?: "We don't have that information yet :(",
                poster_path = "https://image.tmdb.org/t/p/w500$poster_path",
                production_companies = production_companies?.map {
                    it.toProductionCompany()
                } ?: emptyList(),
                release_date = release_date ?: "",
                revenue = revenue ?: 0,
                runtime = runtime ?: 0,
                status = status ?: "",
                title = title ?: "",
                vote_average = vote_average ?: 0.0
            )
        }
    }

    private fun CreditsDto.toCredits() : Credits{
        return Credits(
            cast = cast.map {
                it.toCast()
            }
        )
    }
    
    private fun CastDto.toCast() : Cast{
        
        return Cast(
            id = id,
            name = name,
            profile_path = "https://image.tmdb.org/t/p/w500$profile_path"
        )
    }

    private fun ProductionCompanyDto.toProductionCompany() : ProductionCompany {
        return ProductionCompany(
            id = id,
            logo_path = if(logo_path != null) "https://image.tmdb.org/t/p/w500$logo_path" else "",
            name = name,
            origin_country = origin_country ?:""
        )
    }

    private fun GenreDto.toGenre() : Genre {
        return Genre(
            id = id,
            name = name
        )
    }

}