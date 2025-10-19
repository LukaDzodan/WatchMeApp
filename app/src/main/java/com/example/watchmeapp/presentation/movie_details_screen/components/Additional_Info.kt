package com.example.watchmeapp.presentation.movie_details_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.watchmeapp.ui.theme.SecondaryGreen

@Composable
fun Additional_Info(
    budget: Int,
    originCountry: List<String>,
    original_language: String,
    revenue: Int,
    status: String,
    modifier: Modifier = Modifier,
) {
    val haldScreenWidth = (LocalConfiguration.current.screenWidthDp.dp / 2)

    // Naslov
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Additional Info",
            color = SecondaryGreen,
            style = MaterialTheme.typography.headlineLarge,
            fontFamily = FontFamily.Cursive
        )
    }

    HorizontalDivider(
        modifier = Modifier.width(haldScreenWidth),
        thickness = 2.dp,
        color = SecondaryGreen.copy(alpha = 0.8f)
    )

    Spacer(modifier = Modifier.height(8.dp))

    // Sadržaj
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.Transparent)
            .padding(vertical = 20.dp, horizontal = 16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        InfoRow(label = "💰 Budget", value = "$$budget")
        InfoRow(label = "🌍 Country", value = originCountry.joinToString(", "))
        InfoRow(label = "🗣️ Language", value = original_language.uppercase())
        InfoRow(label = "📈 Revenue", value = "$$revenue")
        InfoRow(label = "🎬 Status", value = status)
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(start = 30.dp, end = 30.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleMedium,
            color = Color.Gray
        )
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            color = Color.Gray,
            textAlign = TextAlign.End
        )
    }
}