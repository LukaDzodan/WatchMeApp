package com.example.watchmeapp.presentation.movie_details_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.watchmeapp.domain.model.movie_details.Genre
import com.example.watchmeapp.ui.theme.PrimaryGreen
import com.example.watchmeapp.ui.theme.SecondaryGreen

@Composable
fun GenreList(
    genres : List<Genre>,
    modifier: Modifier = Modifier) {

    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        genres.onEach {genre ->
            Card(
                modifier = Modifier
                    .height(32.dp),
                colors = CardDefaults.cardColors(
                    containerColor = PrimaryGreen
                ),
                shape = RoundedCornerShape(100.dp),
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = genre.name,
                        style = MaterialTheme.typography.labelLarge,
                        color = Color.Gray,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}