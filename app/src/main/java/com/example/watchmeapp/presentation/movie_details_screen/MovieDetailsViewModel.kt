package com.example.watchmeapp.presentation.movie_details_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.watchmeapp.common.Resource
import com.example.watchmeapp.domain.model.movie.Movie
import com.example.watchmeapp.domain.model.movie_details.MovieDetails
import com.example.watchmeapp.domain.repository.MovieRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    val repository: MovieRepository,
    val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state = mutableStateOf(MovieDetailsState())
    val state: MutableState<MovieDetailsState> = _state

    val movieId = savedStateHandle.get<Int>("id")


    init {
        movieId?.let {
            getMovieDetails(it)
        }

    }

    fun onAction(action: MovieDetailsActions) {

        when (action) {
            MovieDetailsActions.OnBackStack -> {
            }

            MovieDetailsActions.AddToFavouriteClick -> {
                viewModelScope.launch {
                    repository.insertFavoriteMovie(
                        with(_state.value.movieDetails) {
                            Movie(
                                id = id,
                                poster_path = poster_path,
                                release_date = release_date,
                                title = title,
                                vote_average = vote_average
                            )
                        }
                    )
                }
            }

            MovieDetailsActions.RemoveFromFavouriteClick -> {
                viewModelScope.launch {
                    repository.insertFavoriteMovie(
                        with(_state.value.movieDetails) {
                            Movie(
                                id = id,
                                poster_path = poster_path,
                                release_date = release_date,
                                title = title,
                                vote_average = vote_average
                            )
                        }
                    )
                }
            }
        }
    }

    //napravi navigaciju i proledi id kroz saveStateHandle ili vidi nesto novo
    fun getMovieDetails(movieId: Int) {

        _state.value = _state.value.copy(
            isLoading = true
        )

        viewModelScope.launch {
            val response = repository.getMovieDetails(movieId)

            when (response) {
                is Resource.Success<*> -> {
                    _state.value = _state.value.copy(
                        movieDetails = response.data!!,
                        isLoading = false
                    )
                }

                is Resource.Error<*> -> {
                    _state.value = _state.value.copy(
                        errorMessage = response.message ?: "Unknown error",
                        isLoading = false
                    )
                }

                is Resource.Loading<*> -> Unit
            }

        }

    }

}