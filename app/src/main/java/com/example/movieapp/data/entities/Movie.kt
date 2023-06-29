package com.example.movieapp.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "movie")
data class Movie(
    @PrimaryKey(autoGenerate = true) val id_movie: Int = 0,
    val name: String,
    val year: Int,
)
