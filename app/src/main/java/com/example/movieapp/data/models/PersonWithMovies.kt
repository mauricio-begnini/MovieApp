package com.example.movieapp.data.models

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.movieapp.data.entities.Movie
import com.example.movieapp.data.entities.MovieActor
import com.example.movieapp.data.entities.MovieDirector
import com.example.movieapp.data.entities.Person

data class PersonWithMovies(
    @Embedded val person: Person,
    @Relation(
        parentColumn = "id_person",
        entityColumn = "id_movie",
        associateBy = Junction(MovieActor::class)
    )
    val actedMovies: List<Movie>,
    @Relation(
        parentColumn = "id_person",
        entityColumn = "id_movie",
        associateBy = Junction(MovieDirector::class)
    )
    val directedMovies: List<Movie>
)