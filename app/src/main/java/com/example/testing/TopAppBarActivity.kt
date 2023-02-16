package com.example.testing

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Filter
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class TopAppBarActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.Yellow
            ) {
                var appBarState by remember {
                    mutableStateOf(AppBarState())
                }

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = appBarState.title)
                            },
                            actions = {
                                appBarState.actions?.invoke(this)
                            }
                        )
                    }
                ) { 
                    Navigation(navController = navController, startDestination = "") {
                        appBarState = it
                    }

                }
            }
        }
    }
}

@Composable
fun Navigation(navController: NavHostController, startDestination: String, callback: (state: AppBarState) -> Unit) {

    NavHost(
        navController = navController,
        startDestination = "screen_a",
//        modifier = Modifier.padding(
//            values
//        )
    ) {
  
        composable("screen_a") {
            ScreenA(
                onComposing = {
                    callback(it)
                },
                navController = navController
            )
        }
        composable("screen_b") {
            ScreenB(
                onComposing = {
                    callback(it)
                },
                navController = navController
            )
        }
    }
}

@Composable
fun ScreenA(
    onComposing: (AppBarState) -> Unit,
    navController: NavController
) {

    LaunchedEffect(key1 = true) {
        onComposing(
            AppBarState(
                title = "My Screen A",
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = null
                        )
                    }
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.Filter,
                            contentDescription = null
                        )
                    }
                }
            )
        )
    }


    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Screen A"
        )
        Button(onClick = {
            navController.navigate("screen_b")
        }) {
            Text(text = "Navigate to Screen B")
        }
    }
}

@Composable
fun ScreenB(
    onComposing: (AppBarState) -> Unit,
    navController: NavController
) {

    LaunchedEffect(key1 = true) {
        onComposing(
            AppBarState(
                title = "My Screen B",
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = null
                        )
                    }
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = null
                        )
                    }
                }
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Screen B"
        )
        Button(onClick = {
            navController.popBackStack()
        }) {
            Text(text = "Navigate back to Screen A")
        }
    }
}

data class AppBarState(
    val title: String = "111",
    val actions: (@Composable RowScope.() -> Unit)? = null
)