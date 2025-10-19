package com.example.watchmeapp.presentation.movie_details_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.watchmeapp.R
import com.example.watchmeapp.domain.model.movie_details.ProductionCompany
import com.example.watchmeapp.ui.theme.SecondaryGreen

@Composable
fun ProductionCompanyList(
    productionCompanies: List<ProductionCompany>,
    modifier: Modifier = Modifier,
) {

    val halfScreenWidth = (LocalConfiguration.current.screenWidthDp.dp/2)

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Company Production",
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

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        productionCompanies.onEach {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                Text(
                    text = it.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Gray,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                )

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(
                           if (it.logo_path != "") it.logo_path else R.drawable.no_image_available
                        )
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(120.dp,50.dp)
                    )
            }

        }
    }

}