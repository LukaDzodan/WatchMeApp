package com.example.watchmeapp.di

import androidx.room.Room
import com.example.watchmeapp.data.local.MovieDataBase
import com.example.watchmeapp.data.mappers.MovieDetailsMapper
import com.example.watchmeapp.data.mappers.MovieDetailsMapperImpl
import com.example.watchmeapp.data.mappers.MovieMapper
import com.example.watchmeapp.data.mappers.MovieMappersImpl
import com.example.watchmeapp.data.mappers.TrendingMovieMapper
import com.example.watchmeapp.data.mappers.TrendingMovieMappersImpl
import com.example.watchmeapp.data.remote.network.KtorRemoteMovieDataSource
import com.example.watchmeapp.data.remote.network.RemoteMovieDataSource
import com.example.watchmeapp.data.repository.MovieRepositoryImpl
import com.example.watchmeapp.domain.repository.MovieRepository
import com.example.watchmeapp.presentation.movie_details_screen.MovieDetailsViewModel
import com.example.watchmeapp.presentation.movie_list_screen.MovieListViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {

    single<HttpClient> {
        HttpClient(OkHttp){
            install(ContentNegotiation) {
                json(
                    json = Json {
                        ignoreUnknownKeys = true
                    }
                )
            }
            install(HttpTimeout) {
                socketTimeoutMillis = 20_000L
                requestTimeoutMillis = 20_000L
            }
        }
    }

    singleOf(::TrendingMovieMappersImpl).bind<TrendingMovieMapper>()

    singleOf(::MovieMappersImpl).bind<MovieMapper>()

    singleOf(::MovieDetailsMapperImpl).bind<MovieDetailsMapper>()

    singleOf(::KtorRemoteMovieDataSource).bind<RemoteMovieDataSource>()



    singleOf(::MovieRepositoryImpl).bind<MovieRepository>()

    viewModelOf(::MovieListViewModel)
    viewModelOf(::MovieDetailsViewModel)

    single {
        Room.databaseBuilder(
            get(),
            MovieDataBase::class.java,
            "movie_db"
        ).build()
    }

    single { get<MovieDataBase>().dao }

}