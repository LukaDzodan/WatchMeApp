package com.example.watchmeapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.watchmeapp.domain.model.movie.Movie

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey val id: Int,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double,
)


fun Movie.toMovieEntity() : MovieEntity {
    return MovieEntity(
        id = id,
        poster_path = poster_path,
        release_date = release_date,
        title = title,
        vote_average = vote_average,
    )
}
