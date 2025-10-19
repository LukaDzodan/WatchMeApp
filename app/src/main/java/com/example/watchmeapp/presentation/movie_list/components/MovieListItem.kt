package com.example.watchmeapp.presentation.movie_list.components

import android.util.Log
import com.example.watchmeapp.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.error
import coil3.request.fallback
import com.example.watchmeapp.domain.model.movie.Movie
import com.example.watchmeapp.ui.theme.SecondaryGreen

@Composable
fun MovieListItem(
    movie: Movie,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Log.d("slika", movie.poster_path)

    Column() {

        var isImageLoaded by remember { mutableStateOf<Boolean>(false) }


        AsyncImage(
            modifier = modifier
                .padding(8.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .clickable {
                    onClick()
                },
            model = ImageRequest.Builder(LocalContext.current)
                .data(movie.poster_path.takeIf { it.isNotBlank() })
                .error(R.drawable.no_poster_available)
                .fallback(R.drawable.no_poster_available)
                .build()
            ,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
            onError = {
                Log.d("greska", "$it")
            },
            onSuccess = { isImageLoaded = true}
        )

        if (isImageLoaded) {
            Text(
                textAlign = TextAlign.Center,
                color = Color.Gray,
                text = movie.title,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}