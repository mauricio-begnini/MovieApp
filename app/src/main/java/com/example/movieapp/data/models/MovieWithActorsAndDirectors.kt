package com.example.movieapp.data.models

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.movieapp.data.entities.Movie
import com.example.movieapp.data.entities.MovieActor
import com.example.movieapp.data.entities.MovieDirector
import com.example.movieapp.data.entities.Person

data class MovieWithActorsAndDirectors(
    @Embedded val movie: Movie,
    @Relation(
        parentColumn = "id_movie",
        entityColumn = "id_person",
        associateBy = Junction(MovieActor::class)
    )
    val actors: List<Person>,
    @Relation(
        parentColumn = "id_movie",
        entityColumn = "id_person",
        associateBy = Junction(MovieDirector::class)
    )
    val directors: List<Person>
)