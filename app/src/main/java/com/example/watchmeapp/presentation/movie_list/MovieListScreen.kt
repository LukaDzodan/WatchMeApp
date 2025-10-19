package com.example.watchmeapp.presentation.movie_list

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.TabRow
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.watchmeapp.domain.model.movie.Movie
import com.example.watchmeapp.presentation.movie_list.components.MovieList
import com.example.watchmeapp.presentation.movie_list.components.MovieSearchBar
import com.example.watchmeapp.ui.theme.BackGroundGrey
import com.example.watchmeapp.ui.theme.SecondaryGreen
import org.koin.androidx.compose.koinViewModel


@Composable
fun MovieListScreenRoot(
    viewModel: MovieListViewModel = koinViewModel(),
    onMovieClick: (Movie) -> Unit,
) {
    val state by viewModel.state.collectAsState()

    val scrollState = rememberLazyGridState(
        initialFirstVisibleItemIndex = viewModel.trendingScrollPosition,
        initialFirstVisibleItemScrollOffset = viewModel.trendingScrollOffset
    )

    LaunchedEffect(scrollState.firstVisibleItemIndex, scrollState.firstVisibleItemScrollOffset) {
        viewModel.saveScrollState(
            scrollState
        )
    }

    MovieListScreen(
        state = state,
        onActions = { action ->
            when (action) {
                is MovieListActions.OnMovieClick -> onMovieClick(action.movie)
                else -> Unit
            }
            viewModel.onAction(action)
        },
        scrollState = scrollState
    )
}

@Composable
fun MovieListScreen(
    state: MovieListState,
    onActions: (MovieListActions) -> Unit,
    scrollState: LazyGridState,
) {

    val keyBoardController = LocalSoftwareKeyboardController.current

    val pagerState = rememberPagerState { 2 }

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

        MovieSearchBar(
            searchQuery = state.query,
            onSearchQueryChange = { query ->
                onActions(MovieListActions.OnSearchQueryChange(query))
            },
            onImeSearch = {
                keyBoardController?.hide()
            }
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            PrimaryTabRow(
                selectedTabIndex = state.selectedTabIndex,
                modifier = Modifier
                    .fillMaxWidth(),
                containerColor = BackGroundGrey,
                indicator = {
                    TabRowDefaults.SecondaryIndicator(
                        modifier = Modifier
                            .tabIndicatorOffset(state.selectedTabIndex)
                            .fillMaxWidth()
                    )
                }
            ) {

                Tab(
                    selected = state.selectedTabIndex == 0,
                    onClick = {
                        onActions(MovieListActions.OnTabSelected(0))
                    },
                    modifier = Modifier
                        .padding(8.dp)
                ) {
                    Text(
                        text = "Trending movies",
                        color = SecondaryGreen,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                Tab(
                    selected = state.selectedTabIndex == 1,
                    onClick = {
                        onActions(MovieListActions.OnTabSelected(1))
                    },
                    modifier = Modifier
                        .padding(8.dp)
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
            ) { pageIndex ->
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    when (pageIndex) {
                        0 -> {
                            if (state.isLoading) {
                                CircularProgressIndicator()
                            }else{
                                when {
                                    state.errorMessage.isNotEmpty() -> {
                                        Text(
                                            text = state.errorMessage,
                                            style = MaterialTheme.typography.headlineSmall,
                                            color = Color.Gray,
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                    else -> {

                                        MovieList(
                                            movies = state.movies,
                                            onMovieClick = {
                                                onActions(MovieListActions.OnMovieClick(it))
                                            },
                                            loadMore = {
                                                onActions(MovieListActions.LoadMoreMovies)
                                            },
                                            scrollState = scrollState
                                        )
                                    }

// vidi kako da resis scrollState i pocni sa Roomom
                                }
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
                                        onMovieClick = {
                                            onActions(MovieListActions.OnMovieClick(it))
                                        },
                                        loadMore = {},
                                        scrollState = scrollState
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