package com.example.watchmeapp.data.remote.network

import com.example.watchmeapp.common.Constants
import com.example.watchmeapp.common.Resource
import com.example.watchmeapp.data.remote.dto.movie_details_dto.MovieDetailsDto
import com.example.watchmeapp.data.remote.dto.movie_from_query_dto.MovieFromQueryResponse
import com.example.watchmeapp.data.remote.dto.tranding_movies_dto.TradingMoviesResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.headers

class KtorRemoteMovieDataSource(
    private val httpClient: HttpClient
) : RemoteMovieDataSource {

    override suspend fun getTradingMovies(page: Int): Resource<TradingMoviesResponse> {
        return safeCall {
            httpClient.get(
                urlString = "https://api.themoviedb.org/3/trending/movie/day?"
            ) {
                parameter("language", "en-US")
                parameter("page", page)
                parameter("api_key", Constants.api_key)
                headers {
                    append("Accept", "application/json")
                    append("Authorization", "Bearer ${Constants.access_token}")
                }
            }
        }
    }

    override suspend fun getMovieFromQuery(
        page: Int,
        query: String
    ): Resource<MovieFromQueryResponse> {
        return safeCall {
            httpClient.get(
                urlString = "https://api.themoviedb.org/3/search/movie"
            ) {
                parameter("query", query)
                parameter("page", page)
                parameter("api_key", Constants.api_key)
                headers {
                    append("Accept", "application/json")
                    append("Authorization", "Bearer ${Constants.access_token}")
                }
            }
        }
    }

    override suspend fun getMovieDetails(
    movieId : Int
    ): Resource<MovieDetailsDto> {
        return safeCall {
            httpClient.get(
                urlString = "https://api.themoviedb.org/3/movie/$movieId"
            ) {

                parameter("append_to_response", "credits")
                parameter("language", "en-US")
                parameter("api_key", Constants.api_key)

                headers {
                    append("Accept", "application/json")
                    append("Authorization", "Bearer ${Constants.access_token}")
                }
            }
        }
    }

}
