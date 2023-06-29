package com.example.movieapp.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.movieapp.data.entities.MovieActor
import com.example.movieapp.data.entities.MovieDirector

@Dao
interface RelationsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovieActor(movieActor: MovieActor)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovieDirector(movieDirector: MovieDirector)

    @Delete
    suspend fun deleteMovieActor(movieActor: MovieActor)

    @Delete
    suspend fun deleteMovieDirector(movieDirector: MovieDirector)

}