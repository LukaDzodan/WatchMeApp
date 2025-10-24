package com.example.watchmeapp.presentation.movie_list_screen.components

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.watchmeapp.domain.model.movie.Movie

@Composable
fun MovieList(
    movies : List<Movie>,
    isLoading: Boolean = false,
    onMovieClick : (Movie) -> Unit,
    loadMore : () -> Unit,
    modifier: Modifier = Modifier,
    scrollState : LazyGridState
) {

    var isScrolling by remember { mutableStateOf(false) }

    val shouldLoadMore by remember {
        derivedStateOf {
            val lastVisibleItem = scrollState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
            val itemNumber = scrollState.layoutInfo.totalItemsCount
            Log.d("prviitem","$lastVisibleItem")
            lastVisibleItem > itemNumber - 2 && isScrolling
        }
    }

    LaunchedEffect(scrollState.firstVisibleItemIndex) {
        if(scrollState.firstVisibleItemIndex > 0)
        isScrolling = true
    }

    LaunchedEffect(shouldLoadMore) {
        if(shouldLoadMore) {
            loadMore()
        }
    }

    Spacer(modifier = Modifier.height(2.dp))

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
            .fillMaxSize(),
        state = scrollState,
        contentPadding = PaddingValues(16.dp),
    ) {
        items(
            items = movies,
            key = {
                it.id
            }
        ) {movie ->
            MovieListItem(
                movie = movie,
                onClick = {
                    onMovieClick(movie)
                },
                modifier = Modifier
            )
        }

    }

}

