package com.example.watchmeapp.presentation.movie_list

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.watchmeapp.common.Resource
import com.example.watchmeapp.domain.model.movie.Movie
import com.example.watchmeapp.domain.repository.MovieRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieListViewModel(
    private val repository: MovieRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(MovieListState())
    val state = _state.asStateFlow()

    companion object {
        private var trandingMoviePage = 1
        private var movieFromQueryPage = 1
    }

    var trendingScrollPosition by mutableIntStateOf(0)
        private set

    var trendingScrollOffset by mutableIntStateOf(0)
        private set

    var job : Job? = null

    init{
        observeQuery()
        getFavoritesMovies()
    }

    fun observeQuery() {
            state
                .map { it.query }
                .distinctUntilChanged()
                .debounce(500)
                .onEach { query ->
                    job?.cancel()
                    if(query.isBlank()){
                        trandingMoviePage = 1
                        _state.update {
                            it.copy(
                                movies = emptyList()
                            )
                        }
                        job = getTradingMovies(trandingMoviePage)
                    }else {
                        movieFromQueryPage = 1
                        _state.update {
                            it.copy(
                                movies = emptyList()
                            )
                        }
                        job = getMoviesFromQuery(query)
                    }
                }
                .launchIn(viewModelScope)

    }


    fun onAction(action: MovieListActions) {

        when (action) {

            is MovieListActions.OnMovieClick -> {

            }

            is MovieListActions.OnSearchQueryChange -> {
                _state.update {
                    it.copy(
                        query = action.query
                    )
                }
            }

            is MovieListActions.OnTabSelected -> {
                _state.update {
                    it.copy(
                        selectedTabIndex = action.index
                    )
                }
            }

            is MovieListActions.LoadMoreMovies -> {
                if(_state.value.query.isEmpty()){
                    trandingMoviePage++
                    getTradingMovies(trandingMoviePage)
                }else {
                    movieFromQueryPage++
                    getMoviesFromQuery(_state.value.query)
                }
            }
        }
    }

    fun getFavoritesMovies() {

        viewModelScope.launch {
            repository.getFavoritesMovies().collect { favorites ->
                _state.update {
                    it.copy(
                        favouriteMovies = favorites
                    )
                }
            }
        }

    }

    fun getTradingMovies(page: Int) = viewModelScope.launch{

            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            delay(100L)
            when (val response = repository.getTradingMovies(page)) {
                is Resource.Error<*> -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = response.message ?: "Unknown error"
                        )
                    }
                }

                is Resource.Success<*> -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            movies = (it.movies + response.data!!).distinctBy { it.id },
                        )
                    }
                }
                else -> Unit
            }
    }

    fun getMoviesFromQuery(query : String) = viewModelScope.launch{

            _state.update {
                it.copy(
                    isLoading = true
                )
            }

            when (val response = repository.getMovieFromQuery(movieFromQueryPage, query)) {

                is Resource.Success<*> -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = "",
                            movies = (it.movies + response.data!!).distinctBy { it.id },
                        )
                    }
                }
                is Resource.Error<*> -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = response.message ?: "Unknown error"
                        )
                    }
                }

                else -> Unit
            }
    }

    fun saveScrollState(state: LazyGridState) {
        trendingScrollPosition = state.firstVisibleItemIndex
        trendingScrollOffset = state.firstVisibleItemScrollOffset
    }

}