package com.example.movieapp.views

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.R
import com.example.movieapp.data.entities.Movie
import com.example.movieapp.data.entities.MovieActor
import com.example.movieapp.data.entities.MovieDirector
import com.example.movieapp.data.entities.Person

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    movieViewModel: MoviesViewModel = viewModel(factory = MoviesViewModel.Factory),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    Scaffold(
        /*floatingActionButton = {
                               FloatingActionButton(onClick = {
                                   movieViewModel.insertMovie(Movie(name = "Pulp Fiction", year = 1994))
                                   movieViewModel.insertMovie(Movie(name = "Matrix", year = 2001))
                                   movieViewModel.insertMovie(Movie(name = "Django", year = 2012))
                                   movieViewModel.insertMovie(Movie(name = "Jhon Wick", year = 2017))

                                   movieViewModel.insertPerson(Person(name = "Quentin Tarantino"))
                                   movieViewModel.insertPerson(Person(name = "Lilly Wachowski"))
                                   movieViewModel.insertPerson(Person(name = "Lana Wachowski"))
                                   movieViewModel.insertPerson(Person(name = "Chad Stahelski"))
                                   movieViewModel.insertPerson(Person(name = "David Leitch"))
                                   movieViewModel.insertPerson(Person(name = "Bruce Willis"))
                                   movieViewModel.insertPerson(Person(name = "Laurence Fishburne"))
                                   movieViewModel.insertPerson(Person(name = "Carrie-Anne Moss"))
                                   movieViewModel.insertPerson(Person(name = "Jamie Foxx"))
                                   movieViewModel.insertPerson(Person(name = "Christoph Waltz"))
                                   movieViewModel.insertPerson(Person(name = "Keanu Reeves"))

                                   movieViewModel.insertDirector(MovieDirector(1, 1))
                                   movieViewModel.insertDirector(MovieDirector(3, 1))
                                   movieViewModel.insertDirector(MovieDirector(2, 2))
                                   movieViewModel.insertDirector(MovieDirector(2, 3))
                                   movieViewModel.insertDirector(MovieDirector(4, 4))
                                   movieViewModel.insertDirector(MovieDirector(4, 5))

                                   movieViewModel.insertActor(MovieActor(1, 1))
                                   movieViewModel.insertActor(MovieActor(3, 1))
                                   movieViewModel.insertActor(MovieActor(1, 6))
                                   movieViewModel.insertActor(MovieActor(4, 7))
                                   movieViewModel.insertActor(MovieActor(4, 8))
                                   movieViewModel.insertActor(MovieActor(3, 9))
                                   movieViewModel.insertActor(MovieActor(3, 10))
                                   movieViewModel.insertActor(MovieActor(2, 11))
                                   movieViewModel.insertActor(MovieActor(4, 11))
                               }) {
                                   Icon(imageVector = Icons.Default.Add, contentDescription = "Add Icon")
                               }
        },*/
        bottomBar = {
            NavigationBar {
                bottomNavItems.forEach { item ->
                    val selected = item.route == backStackEntry.value?.destination?.route
                    NavigationBarItem(
                        selected = selected,
                        onClick = { navController.navigate(item.route) },
                        label = {
                            Text(text = item.name, fontWeight = FontWeight.SemiBold)
                        },
                        icon = { Icon(painter = painterResource(id = item.icon), contentDescription = "${item.name} icon")}
                    )
                }
            }
        }
    ) {
        NavHost(modifier = Modifier.padding(it),navController = navController, startDestination = "movies" ){
            composable(route = "movies"){
                MovieScreen(movieViewModel = movieViewModel)
            }
            composable(route = "persons"){
                PersonsScreen(movieViewModel = movieViewModel)
            }
        }
    }
}

@Composable
fun MovieScreen(movieViewModel: MoviesViewModel) {
    val movies by movieViewModel.movies.collectAsState()
    LazyColumn{
        items(movies) { movie ->
            Card(modifier = Modifier.fillMaxWidth()) {
                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "${movie.id_movie}")
                    Text(text = "${movie.name}")
                    Text(text = "${movie.year}")
                }
            }
        }
    }
}

@Composable
fun PersonsScreen(movieViewModel: MoviesViewModel) {
    val persons by movieViewModel.persons.collectAsState()
    LazyColumn{
        items(persons) { person ->
            Card(modifier = Modifier.fillMaxWidth()) {
                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "${person.id_person}")
                    Text(text = "${person.name}")
                }
            }
        }
    }
}

data class BottomNavItem(
    val name: String,
    val route: String,
    @DrawableRes val icon: Int,
)

val bottomNavItems = listOf(
    BottomNavItem(
        name = "Movies",
        route = "movies",
        icon = R.drawable.baseline_movie_24
    ),
    BottomNavItem(
        name = "Persons",
        route = "persons",
        icon = R.drawable.baseline_recent_actors_24
    ),
    /*BottomNavItem(
        name = "Settings",
        route = "settings",
        icon =
    ),*/
)
