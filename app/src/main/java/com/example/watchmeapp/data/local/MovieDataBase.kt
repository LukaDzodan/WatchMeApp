package com.example.watchmeapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.watchmeapp.data.local.dao.MovieDao

@Database(
    entities = [MovieEntity::class],
    version = 1
)
abstract class MovieDataBase() : RoomDatabase() {

    abstract val dao : MovieDao

}