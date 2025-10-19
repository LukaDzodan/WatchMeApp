package com.example.watchmeapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.example.watchmeapp.domain.model.movie.Movie
import com.example.watchmeapp.domain.model.movie_details.Cast
import com.example.watchmeapp.domain.model.movie_details.Credits
import com.example.watchmeapp.domain.model.movie_details.Genre
import com.example.watchmeapp.domain.model.movie_details.MovieDetails
import com.example.watchmeapp.domain.model.movie_details.ProductionCompany
import com.example.watchmeapp.presentation.movie_details_screen.MovieDetailsScreen
import com.example.watchmeapp.presentation.movie_list.MovieListScreen
import com.example.watchmeapp.presentation.movie_list.MovieListState
import com.example.watchmeapp.presentation.movie_list.components.MovieListItem
import com.example.watchmeapp.presentation.movie_list.components.MovieSearchBar


//@Preview
//@Composable
//fun MovieListItemPreview() {
//    MovieListItem(
//        movie = movie,
//        onClick = {},
//    )
//}

@Preview
@Composable
fun MovieSearchBarPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red),
        contentAlignment = Alignment.Center
    ) {
        MovieSearchBar(
            searchQuery = "",
            onSearchQueryChange = { },
            onImeSearch = { },
        )
    }
}

//@Preview
//@Composable
//fun MovieListScreenPreview() {
//    MovieListScreen(
//        state = MovieListState(),
//        onActions = {},
//
//    )
//}

val genres = listOf(
    Genre(1, "Akcija"),
    Genre(2, "Avantura"),
    Genre(3, "Komedija"),
)

val previewMovieDetails = MovieDetails(
    backdrop_path = "https://image.tmdb.org/t/p/w500/8Y43POKjjKDGI9MH89NW0NAzzp8.jpg",
    budget = 200000000,
    credits = Credits(
        cast = listOf(
            Cast(
                id = 3223,
                name = "Robert Downey Jr.",
                profile_path = "/1YjdSym1jTG7xjHSI0yGGWEsw5i.jpg"
            ),
            Cast(
                id = 16828,
                name = "Chris Evans",
                profile_path = "/3bOGNsHlrswhyW79uvIHH1V43JI.jpg"
            ),
            Cast(
                id = 16828,
                name = "Chris Evans",
                profile_path = "/3bOGNsHlrswhyW79uvIHH1V43JI.jpg"
            ),
            Cast(
                id = 16828,
                name = "Chris Evans",
                profile_path = "/3bOGNsHlrswhyW79uvIHH1V43JI.jpg"
            ),
            Cast(
                id = 16828,
                name = "Chris Evans",
                profile_path = "/3bOGNsHlrswhyW79uvIHH1V43JI.jpg"
            ), Cast(
                id = 16828,
                name = "Chris Evans",
                profile_path = "/3bOGNsHlrswhyW79uvIHH1V43JI.jpg"
            ),
            Cast(
                id = 16828,
                name = "Chris Evans",
                profile_path = "/3bOGNsHlrswhyW79uvIHH1V43JI.jpg"
            ),
            Cast(
                id = 1245,
                name = "Scarlett Johansson",
                profile_path = "/6NsMbJXRlDZuDzatN2akFdGuTvx.jpg"
            )
        ),
    ),
    genres = listOf(
        Genre(28, "Action"),
        Genre(12, "Adventure"),
        Genre(878, "Science Fiction")
    ),
    homepage = "https://www.marvel.com/movies/avengers-endgame",
    id = 299534,
    imdb_id = "tt4154796",
    origin_country = listOf("US"),
    original_language = "en",
    original_title = "Avengers: Endgame",
    overview = "After the devastating events of Avengers: Infinity War, the universe is in ruins. With the help of remaining allies, the Avengers assemble once more.",
    poster_path = "https://image.tmdb.org/t/p/w500/or06FN3Dka5tukK1e9sl16pB3iy.jpg",
    production_companies = listOf(
        ProductionCompany(
            id = 420,
            logo_path = "https://image.tmdb.org/t/p/w500/hUzeosd33nzE5MCNsZxCGEKTXaQ.png",
            name = "Marvel Studios",
            origin_country = "US"
        ),
        ProductionCompany(
            id = 420,
            logo_path = "https://image.tmdb.org/t/p/w500/hUzeosd33nzE5MCNsZxCGEKTXaQ.png",
            name = "Marvel Studios",
            origin_country = "US"
        )
        ,
        ProductionCompany(
            id = 420,
            logo_path = "https://image.tmdb.org/t/p/w500/hUzeosd33nzE5MCNsZxCGEKTXaQ.png",
            name = "Marvel Studios",
            origin_country = "US"
        )
    ),
    release_date = "2019-04-24",
    revenue = 279780056,
    runtime = 181,
    status = "Released",
    title = "Avengers: Endgame",
    vote_average = 8.3
)

//@Preview
//@Composable
//fun MovieDetailsScreenPreview() {
//    MovieDetailsScreen(
//        movie = moviedetails,
//    )
//
//}