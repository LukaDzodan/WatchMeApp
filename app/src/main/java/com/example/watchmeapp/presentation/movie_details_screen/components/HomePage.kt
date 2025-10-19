package com.example.watchmeapp.presentation.movie_details_screen.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdsClick
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import androidx.core.net.toUri
import com.example.watchmeapp.ui.theme.SecondaryGreen

@Composable
fun HomePage(
    posterUrl: String,
    homePageLink: String,
    modifier: Modifier = Modifier,
) {
    val halfScreenWidth = (LocalConfiguration.current.screenWidthDp.dp / 2)
    val contex = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Home Page",
            color = SecondaryGreen,
            style = MaterialTheme.typography.headlineLarge,
            fontFamily = FontFamily.Cursive
        )

    }
    HorizontalDivider(
        modifier = Modifier
            .width(halfScreenWidth)
        ,
        thickness = 2.dp,
        color = SecondaryGreen
    )

    Spacer(modifier = Modifier.height(12.dp))

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    )
    {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(posterUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp,150.dp)
        )
            Button(
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, homePageLink.toUri())
                    contex.startActivity(intent)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = SecondaryGreen
                ),
                border = BorderStroke(2.dp,SecondaryGreen)
            ) {
                Text(
                    text = "Home page",
                    style = MaterialTheme.typography.titleMedium,
                    color = SecondaryGreen,
                    textAlign = TextAlign.End
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = Icons.Default.AdsClick,
                    contentDescription = null,
                    tint = SecondaryGreen,
                    modifier = Modifier.size(30.dp)
                )
            }
    }
}