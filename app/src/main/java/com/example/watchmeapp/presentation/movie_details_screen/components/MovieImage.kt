package com.example.watchmeapp.presentation.movie_details_screen.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.SaveAlt
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.watchmeapp.ui.theme.BackGroundGrey
import com.example.watchmeapp.ui.theme.SecondaryGreen
import kotlinx.coroutines.launch

@Composable
fun MovieImage(
    imageUrl: String?,
    isMovieSaved: Boolean,
    onBackStack: () -> Unit,
    onMovieSave: () -> Unit,
    onMovieDelete: () -> Unit,
    showSnackBar : (String) -> Unit,
    scrollState: LazyListState,
    modifier: Modifier = Modifier,
) {

    val snackbarHostState = remember { SnackbarHostState() }

    val scrollOffSet = minOf(
        1f,
        scrollState.firstVisibleItemScrollOffset / 400f
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .matchParentSize()
                .graphicsLayer(
                    alpha = 1f - scrollOffSet
                )
        )

        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            BackGroundGrey
                        ),
                        startY = with(LocalDensity.current) { (200.dp.toPx() * 0.9f) },
                    )
                )
        )
        IconButton(
            onClick = {
                onBackStack()
            },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(12.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ChevronLeft,
                contentDescription = null,
                tint = SecondaryGreen,
                modifier = Modifier.size(50.dp)
            )
        }

        when {

            isMovieSaved == true -> {
                IconButton(
                    onClick = { onMovieDelete() },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(12.dp),
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = SecondaryGreen,
                        modifier = Modifier.size(50.dp)
                    )
                }
            }

            else -> {
                IconButton(
                    onClick = { onMovieSave()
                                showSnackBar("Movie is saved to the favorites list")
                              },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(12.dp),
                ) {
                    Icon(
                        imageVector = Icons.Default.StarOutline,
                        contentDescription = null,
                        tint = SecondaryGreen,
                        modifier = Modifier.size(50.dp)
                    )
                }
            }

        }

    }
}