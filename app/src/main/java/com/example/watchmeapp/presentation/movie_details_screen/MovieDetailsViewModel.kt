package com.example.watchmeapp.presentation.movie_details_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.watchmeapp.common.Resource
import com.example.watchmeapp.domain.model.movie.Movie
import com.example.watchmeapp.domain.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    val repository: MovieRepository,
    val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state = mutableStateOf(MovieDetailsState())
    val state: MutableState<MovieDetailsState> = _state

    val movieId = savedStateHandle.get<Int>("id")
    val isMovieSaved = savedStateHandle.get<Boolean>("isSaved") == true

    init {
        movieId?.let {
            getMovieDetails(it)
        }

        _state.value = _state.value.copy(
            isMovieSaved = isMovieSaved
        )
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
                    _state.value = _state.value.copy(
                        isMovieSaved = true
                    )
                }
            }

            MovieDetailsActions.DeleteFromFavouriteClick -> {
                viewModelScope.launch {
                    repository.deleteFavoriteMovie(
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
                    _state.value = _state.value.copy(
                        isMovieSaved = false
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