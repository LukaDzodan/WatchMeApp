package com.example.watchmeapp.presentation.movie_list_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.watchmeapp.domain.model.movie.Movie
import com.example.watchmeapp.presentation.movie_list_screen.components.MovieList
import com.example.watchmeapp.presentation.movie_list_screen.components.MovieSearchBar
import com.example.watchmeapp.ui.theme.BackGroundGrey
import com.example.watchmeapp.ui.theme.SecondaryGreen
import org.koin.androidx.compose.koinViewModel

@SuppressLint("FrequentlyChangingValue")
@Composable
fun MovieListScreenRoot(
    viewModel: MovieListViewModel = koinViewModel(),
    onMovieClick: (Movie, Boolean) -> Unit,
) {
    val state by viewModel.state.collectAsState()

    MovieListScreen(
        state = state, onActions = { action ->
            when (action) {
                is MovieListActions.OnMovieClick -> onMovieClick(action.movie, action.isSaved)
                else -> Unit
            }
            viewModel.onAction(action)
        })
}

@Composable
fun MovieListScreen(
    state: MovieListState, onActions: (MovieListActions) -> Unit
) {

    val trendingMovieScrollState = rememberLazyGridState()
    val favoriteMovieScrollState = rememberLazyGridState()

    val keyBoardController = LocalSoftwareKeyboardController.current

    val pagerState = rememberPagerState(pageCount = { 2 })

    LaunchedEffect(pagerState.currentPage) {
        onActions(MovieListActions.OnTabSelected(pagerState.currentPage))
    }

    LaunchedEffect(state.selectedTabIndex) {
        pagerState.animateScrollToPage(state.selectedTabIndex)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(BackGroundGrey)
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        MovieSearchBar(searchQuery = state.query, onSearchQueryChange = { query ->
            onActions(MovieListActions.OnSearchQueryChange(query))
        }, onImeSearch = {
            keyBoardController?.hide()
        })

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            PrimaryTabRow(
                selectedTabIndex = state.selectedTabIndex,
                modifier = Modifier.fillMaxWidth(),
                containerColor = BackGroundGrey,
                indicator = {
                    TabRowDefaults.SecondaryIndicator(
                        modifier = Modifier
                            .tabIndicatorOffset(state.selectedTabIndex)
                            .fillMaxWidth()
                    )
                }) {

                Tab(
                    selected = state.selectedTabIndex == 0, onClick = {
                        onActions(MovieListActions.OnTabSelected(0))
                    }, modifier = Modifier.padding(8.dp)
                ) {
                    Text(
                        text = "Trending movies",
                        color = SecondaryGreen,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                Tab(
                    selected = state.selectedTabIndex == 1, onClick = {
                        onActions(MovieListActions.OnTabSelected(1))
                    }, modifier = Modifier.padding(8.dp)
                ) {
                    Text(
                        text = "Favorites",
                        color = SecondaryGreen,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

            }

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                beyondViewportPageCount = 1
            ) { pageIndex ->
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    when (pageIndex) {
                        0 -> {
                            when {
                                state.errorMessage.isNotEmpty() -> {
                                    Text(
                                        text = state.errorMessage,
                                        style = MaterialTheme.typography.headlineSmall,
                                        color = Color.Gray,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.padding(12.dp)
                                    )
                                }

                                else -> {

                                    MovieList(
                                        movies = state.movies,
                                        onMovieClick = { movie ->
                                            onActions(
                                                MovieListActions.OnMovieClick(
                                                    movie, state.favouriteMovies.any {
                                                        it.id == movie.id
                                                    })
                                            )
                                        },
                                        loadMore = {
                                            onActions(MovieListActions.LoadMoreMovies)
                                        },
                                        scrollState = trendingMovieScrollState,
                                        isLoading = state.isLoading
                                    )
                                }

// vidi kako da resis scrollState i pocni sa Roomom
                            }

                        }

                        1 -> {
                            when {

                                state.favouriteMovies.isEmpty() -> {
                                    Text(
                                        text = "No favourite movies yet",
                                        style = MaterialTheme.typography.headlineSmall,
                                        color = Color.Gray,
                                        textAlign = TextAlign.Center
                                    )
                                }

                                else -> {
                                    MovieList(
                                        movies = state.favouriteMovies,
                                        onMovieClick = { movie ->
                                            onActions(
                                                MovieListActions.OnMovieClick(
                                                    movie, state.favouriteMovies.any {
                                                        it.id == movie.id
                                                    })
                                            )
                                        },
                                        loadMore = {},
                                        scrollState = favoriteMovieScrollState,
                                        isLoading = false
                                    )
                                }

                            }
                        }
                    }
                }
            }
        }

    }

}