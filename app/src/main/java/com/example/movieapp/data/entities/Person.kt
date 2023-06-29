package com.example.movieapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person")
data class Person(
    @PrimaryKey(autoGenerate = true) val id_person: Int = 0,
    val name: String,
)
