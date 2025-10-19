package com.example.watchmeapp.presentation.movie_details_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.watchmeapp.domain.model.movie_details.Cast
import com.example.watchmeapp.ui.theme.PrimaryGreen
import com.example.watchmeapp.ui.theme.SecondaryGreen

@Composable
fun CastList(
    casts : List<Cast>,
) {

    val haldScreenWidth = (LocalConfiguration.current.screenWidthDp.dp/2)

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Cast",
            color = SecondaryGreen,
            style = MaterialTheme.typography.headlineLarge,
            fontFamily = FontFamily.Cursive
        )

    }
    HorizontalDivider(
        modifier = Modifier
            .width(haldScreenWidth)
        ,
        thickness = 2.dp,
        color = SecondaryGreen
    )

    Spacer(modifier = Modifier.height(8.dp))

    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        items(casts) {cast ->
            Card(
                modifier = Modifier
                    .height(32.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent
                ),
                shape = RoundedCornerShape(100.dp),
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    AsyncImage(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(RoundedCornerShape(50)),
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("https://image.tmdb.org/t/p/w500"+cast.profile_path)
                            .crossfade(true)
                            .build(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = cast.name,
                        style = MaterialTheme.typography.labelLarge,
                        color = Color.Gray,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

    }

}