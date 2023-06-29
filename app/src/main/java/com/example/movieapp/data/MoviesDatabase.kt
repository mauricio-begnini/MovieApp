package com.example.movieapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movieapp.data.daos.MovieDao
import com.example.movieapp.data.daos.PersonDao
import com.example.movieapp.data.daos.RelationsDao
import com.example.movieapp.data.entities.Movie
import com.example.movieapp.data.entities.MovieActor
import com.example.movieapp.data.entities.MovieDirector
import com.example.movieapp.data.entities.Person

@Database(
    entities = [Movie::class, Person::class, MovieActor::class, MovieDirector::class],
    version = 1,
    exportSchema = false
)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    abstract fun personDao(): PersonDao

    abstract fun relationsDao(): RelationsDao

    companion object {

        @Volatile
        private var INSTANCE: MoviesDatabase? = null

        fun getDatabase(context: Context): MoviesDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    MoviesDatabase::class.java,
                    "MoviesDatabase"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}