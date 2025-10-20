package com.example.watchmeapp.presentation.movie_details_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.watchmeapp.presentation.movie_details_screen.components.Additional_Info
import com.example.watchmeapp.presentation.movie_details_screen.components.CastList
import com.example.watchmeapp.presentation.movie_details_screen.components.GenreList
import com.example.watchmeapp.presentation.movie_details_screen.components.HomePage
import com.example.watchmeapp.presentation.movie_details_screen.components.ImdbIcon
import com.example.watchmeapp.presentation.movie_details_screen.components.MovieImage
import com.example.watchmeapp.presentation.movie_details_screen.components.ProductionCompanyList
import com.example.watchmeapp.ui.theme.BackGroundGrey
import org.koin.androidx.compose.koinViewModel
import kotlin.math.round

@Composable
fun MovieDetailsScreenRoot(
    viewModel: MovieDetailsViewModel = koinViewModel(),
    onBackStack: () -> Unit,
) {

    val state = viewModel.state

    MovieDetailsScreen(
        state = state.value,
        onAction = {
            when (it) {
                MovieDetailsActions.OnBackStack -> onBackStack()
                else -> Unit
            }
            viewModel.onAction(it)
        }
    )

}
//hendalj greske npr. ako nema slike neka fejk, ocenu zaokruzi na 1 dec, napravi bolji raspored company productiona
@Composable
fun MovieDetailsScreen(
    state: MovieDetailsState,
    onAction: (MovieDetailsActions) -> Unit,
    modifier: Modifier = Modifier,
) {

    val scrollState = rememberLazyListState()

    if (state.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize().background(BackGroundGrey),
            contentAlignment = Alignment.Center
        )
        {
            CircularProgressIndicator()
        }
    } else {
        when {
            state.errorMessage.isNotEmpty() -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                )
                {
                    Text(
                        text = state.errorMessage,
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.Red
                    )
                }
            }

            else -> {
                LazyColumn(
                    state = scrollState,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(BackGroundGrey)
                        .statusBarsPadding()
                        .navigationBarsPadding(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                        MovieImage(
                            imageUrl = state.movieDetails.backdrop_path,
                            scrollState = scrollState,
                            onBackStack = {
                                onAction(MovieDetailsActions.OnBackStack)
                            },
                            onMovieSave = {
                                onAction(MovieDetailsActions.AddToFavouriteClick)
                            },
                            onMovieDelete = {
                                onAction(MovieDetailsActions.DeleteFromFavouriteClick)
                            },
                            isMovieSaved = state.isMovieSaved,
                            showSnackBar = {}
                        )
                    }
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = state.movieDetails.title,
                                style = MaterialTheme.typography.displaySmall,
                                color = Color.Gray,
                                textAlign = TextAlign.Center,
                                fontFamily = FontFamily.Serif
                            )
                        }
                    }

                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(intrinsicSize = IntrinsicSize.Min),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text(
                                text = state.movieDetails.runtime.toString() + " min",
                                style = MaterialTheme.typography.labelLarge,
                                color = Color.Gray,
                                textAlign = TextAlign.Center,
                                fontFamily = FontFamily.Serif,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = state.movieDetails.release_date,
                                style = MaterialTheme.typography.labelLarge,
                                color = Color.Gray,
                                textAlign = TextAlign.Center,
                                fontFamily = FontFamily.Serif,
                                fontWeight = FontWeight.Bold
                            )

                            ImdbIcon(
                                vote_average = state.movieDetails.vote_average.toTwoDecimal()
                            )
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp, end = 20.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        )
                        {
                            Text(
                                text = state.movieDetails.overview,
                                style = MaterialTheme.typography.labelLarge,
                                color = Color.Gray,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.height(20.dp))
                    }

                    item {
                        GenreList(
                            genres = state.movieDetails.genres
                        )
                    }
                    item {
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                    item {
                        CastList(
                            casts = state.movieDetails.credits.cast
                        )
                    }
                    item {
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                    item {
                        Additional_Info(
                            budget = state.movieDetails.budget,
                            originCountry = state.movieDetails.origin_country,
                            original_language = state.movieDetails.original_language,
                            revenue = state.movieDetails.revenue,
                            status = state.movieDetails.status,
                        )
                    }
                    item {
                        Spacer(modifier = Modifier.height(10.dp))
                    }

                    item {
                        ProductionCompanyList(
                            productionCompanies = state.movieDetails.production_companies
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.height(10.dp))
                    }

                    item {
                        HomePage(
                            state.movieDetails.poster_path ?: "",
                            state.movieDetails.homepage
                        )
                    }

                }
            }
        }
    }
}

fun Double.toTwoDecimal() : Double {

    return round(this * 10.0) / 10.0

}
