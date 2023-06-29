package com.example.movieapp.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.movieapp.data.models.PersonWithMovies
import com.example.movieapp.data.entities.Person
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {

    @Transaction
    @Query("SELECT * FROM person")
    fun getPersonsWithMovies(): Flow<List<PersonWithMovies>>

    @Transaction
    @Query("SELECT * FROM person WHERE id_person = :personId")
    fun getPersonWithMovies(personId: Int): Flow<PersonWithMovies>?

    @Query("SELECT * FROM person")
    fun getAllPersons(): Flow<List<Person>>

    @Query("SELECT * FROM person WHERE id_person = :personId")
    fun getPersonById(personId: Int): Flow<Person>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPerson(person: Person)

    @Update
    suspend fun updatePerson(person: Person)

    @Delete
    suspend fun deletePerson(person: Person)

}