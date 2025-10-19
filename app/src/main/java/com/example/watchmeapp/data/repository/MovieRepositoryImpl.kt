package com.example.watchmeapp.data.repository

import android.util.Log
import com.example.watchmeapp.common.Resource
import com.example.watchmeapp.data.local.dao.MovieDao
import com.example.watchmeapp.data.local.toMovieEntity
import com.example.watchmeapp.data.mappers.MovieDetailsMapper
import com.example.watchmeapp.data.mappers.MovieMapper
import com.example.watchmeapp.data.mappers.TrendingMovieMapper
import com.example.watchmeapp.data.remote.network.RemoteMovieDataSource
import com.example.watchmeapp.domain.model.movie.Movie
import com.example.watchmeapp.domain.model.movie_details.MovieDetails
import com.example.watchmeapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepositoryImpl(
    private val dao : MovieDao,
    private val remoteMovieDataSource: RemoteMovieDataSource,
    private val movieMapper: MovieMapper,
    private val trandingMovieMapper: TrendingMovieMapper,
    private val movieDetailsMapper : MovieDetailsMapper
) : MovieRepository {

    override suspend fun getTradingMovies(page: Int): Resource<List<Movie>> {
        val response = remoteMovieDataSource.getTradingMovies(page)
        Log.d("filmovi", "${response.message}")
        return when (response) {
            is Resource.Error -> Resource.Error(response.message!!)
            is Resource.Loading -> Resource.Loading()
            is Resource.Success -> Resource.Success(
                response.data?.results!!.map {
                    trandingMovieMapper.map(it)
                }
            )
        }
    }

    override suspend fun getMovieFromQuery(page: Int, query: String): Resource<List<Movie>> {
        val response = remoteMovieDataSource.getMovieFromQuery(page,query)
        Log.d("srcc", "${response.data?.results?.size}")
        return when (response) {
            is Resource.Error -> Resource.Error(response.message!!)
            is Resource.Loading -> Resource.Loading()
            is Resource.Success -> Resource.Success(
                response.data?.results!!.map {
                    movieMapper.map(it)
                }
            )
        }
    }

    override suspend fun getMovieDetails(movieId: Int): Resource<MovieDetails> {
        val response = remoteMovieDataSource.getMovieDetails(movieId)
        Log.d("detalji", "${response.data?.title}")
        return when(response) {
            is Resource.Error -> Resource.Error(response.message!!)
            is Resource.Loading -> Resource.Loading()
            is Resource.Success -> Resource.Success(
                movieDetailsMapper.map(response.data!!)
            )
        }
    }

    override suspend fun getFavoritesMovies(): Flow<List<Movie>> {
        return dao.getFavoritesMovies().map {
            it.map {
                 movieMapper.map(it)
            }
        }
    }

    override suspend fun insertFavoriteMovie(movie: Movie) {
        dao.inserFavoritetMovie(movie.toMovieEntity())
    }

    override suspend fun deleteFavoriteMovie(movie: Movie) {
        dao.deleteFavoriteMovie(movie.toMovieEntity())
    }
}


