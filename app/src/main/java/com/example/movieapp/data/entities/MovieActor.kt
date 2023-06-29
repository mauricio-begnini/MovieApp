package com.example.movieapp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "movie_actors",
    primaryKeys = ["id_movie", "id_person"]
)
data class MovieActor (
    @ColumnInfo(name = "id_movie") val idMovie: Int,
    @ColumnInfo(name = "id_person") val idActor: Int,
)