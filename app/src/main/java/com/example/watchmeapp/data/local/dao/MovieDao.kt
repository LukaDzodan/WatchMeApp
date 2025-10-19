package com.example.watchmeapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.watchmeapp.data.local.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun getFavoritesMovies() : Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun inserFavoritetMovie(movie : MovieEntity)

    @Delete
    suspend fun deleteFavoriteMovie(movie : MovieEntity)




}