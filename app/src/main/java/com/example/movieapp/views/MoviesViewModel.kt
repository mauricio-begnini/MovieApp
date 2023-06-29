package com.example.movieapp.views

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.movieapp.MoviesApplication
import com.example.movieapp.data.daos.MovieDao
import com.example.movieapp.data.daos.PersonDao
import com.example.movieapp.data.daos.RelationsDao
import com.example.movieapp.data.entities.Movie
import com.example.movieapp.data.entities.MovieActor
import com.example.movieapp.data.entities.MovieDirector
import com.example.movieapp.data.entities.Person
import com.example.movieapp.data.models.MovieWithActorsAndDirectors
import com.example.movieapp.data.models.PersonWithMovies
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class MoviesViewModel(
    private val movieDao: MovieDao,
    private val personDao: PersonDao,
    private val relationsDao: RelationsDao,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val movieId: MutableStateFlow<Int> = MutableStateFlow(0)
    private val personId: MutableStateFlow<Int> = MutableStateFlow(0)

    val movies: StateFlow<List<Movie>> = movieDao.getAllMovies().map {
        it
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000L),
        initialValue = listOf()
    )

    val movieWithActorsAndDirectors: StateFlow<MovieWithActorsAndDirectors> = movieDao.getMovieWithActorsAndDirectors(movieId.value)?.map {
        it
    }?.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000L),
        initialValue = MovieWithActorsAndDirectors(Movie(0, "", 0), listOf(), listOf())
    ) ?: MutableStateFlow(MovieWithActorsAndDirectors(Movie(0, "", 0), listOf(), listOf()))

    val persons: StateFlow<List<Person>> = personDao.getAllPersons().map {
        it
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000L),
        initialValue = listOf()
    )

    val personWithMovies: StateFlow<PersonWithMovies> = personDao.getPersonWithMovies(personId.value)?.map {
        it
    }?.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000L),
        initialValue = PersonWithMovies(Person(0, ""), listOf(), listOf())
    ) ?: MutableStateFlow(PersonWithMovies(Person(0, ""), listOf(), listOf()))


    fun insertMovie(movie: Movie){
        viewModelScope.launch {
            movieDao.insertMovie(movie)
        }
    }

    fun insertPerson(person: Person){
        viewModelScope.launch {
            personDao.insertPerson(person)
        }
    }

    fun insertActor(movieActor: MovieActor){
        viewModelScope.launch {
            relationsDao.insertMovieActor(movieActor)
        }
    }

    fun insertDirector(movieDirector: MovieDirector){
        viewModelScope.launch {
            relationsDao.insertMovieDirector(movieDirector)
        }
    }


    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val application = checkNotNull(extras[APPLICATION_KEY])
                val savedStateHandle = extras.createSavedStateHandle()

                return MoviesViewModel(
                    (application as MoviesApplication).database.movieDao(),
                    application.database.personDao(),
                    application.database.relationsDao(),
                    savedStateHandle,
                ) as T
            }
        }
    }

}