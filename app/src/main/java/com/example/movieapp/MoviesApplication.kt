package com.example.movieapp

import android.app.Application
import com.example.movieapp.data.MoviesDatabase

class MoviesApplication : Application() {

    val database: MoviesDatabase by lazy {
        MoviesDatabase.getDatabase(this)
    }

}