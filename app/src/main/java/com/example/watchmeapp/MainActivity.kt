package com.example.watchmeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.watchmeapp.common.Screens
import com.example.watchmeapp.presentation.movie_details_screen.MovieDetailsScreen
import com.example.watchmeapp.presentation.movie_details_screen.MovieDetailsScreenRoot
import com.example.watchmeapp.presentation.movie_list.MovieListScreen
import com.example.watchmeapp.presentation.movie_list.MovieListScreenRoot
import com.example.watchmeapp.presentation.movie_list.MovieListState
import com.example.watchmeapp.presentation.movie_list.MovieListViewModel
import com.example.watchmeapp.presentation.movie_list.components.MovieSearchBar
import com.example.watchmeapp.ui.theme.WatchMeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WatchMeAppTheme {

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Screens.MovieListScreen.route
                ) {

                    composable(
                        route = Screens.MovieListScreen.route
                    ) {
                        MovieListScreenRoot(
                            onMovieClick = { movie,isSaved ->
                                navController.navigate(Screens.MovieDetailsScreen.route+"/${movie.id}/$isSaved")
                            }
                        )
                    }

                    composable(
                        route = Screens.MovieDetailsScreen.route+"/{id}/{isSaved}",
                        arguments = listOf(
                            navArgument(
                                name = "id"
                            ) {
                                type = NavType.IntType
                            },
                            navArgument(
                                name = "isSaved"
                            ) {
                                type = NavType.BoolType
                            }
                        )
                    ) {
                        MovieDetailsScreenRoot(
                            onBackStack = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}
