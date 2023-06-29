package com.example.movieapp.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.movieapp.data.models.MovieWithActorsAndDirectors
import com.example.movieapp.data.entities.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getAllMovies(): Flow<List<Movie>>

    @Query("SELECT * FROM movie WHERE id_movie = :movieId")
    fun getMovieById(movieId: Int): Flow<Movie>?

    @Query("SELECT * FROM movie")
    @Transaction
    fun getMoviesWithActorsAndDirectors(): Flow<List<MovieWithActorsAndDirectors>>

    @Query("SELECT * FROM movie WHERE id_movie = :id")
    @Transaction
    fun getMovieWithActorsAndDirectors(id: Int): Flow<MovieWithActorsAndDirectors>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovie(movie: Movie)

    @Update
    suspend fun updateMovie(movie: Movie)

    @Delete
    suspend fun deleteMovie(movie: Movie)

}