package com.example.composedestinations

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatListBulleted
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composedestinations.destinations.SecondScreenDestination
import com.example.composedestinations.ui.theme.ComposeDestinationsTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

           DestinationsNavHost(navGraph = NavGraphs.root)


        }
    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@RootNavGraph(start = true)
@Destination
@Composable
fun FirstScreen(navigator: DestinationsNavigator) {

    BasicText(text = "wowowo")
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray),

        ) {
        BasicText(
            style = TextStyle(color = Color.Red, fontSize = 42.sp),
            modifier = Modifier.offset(y = 140.dp),
            text = "Use bottom navigation bar to navigate to second screen and you will see it disappear"
        )
    }
    val items = listOf(
        BottomNavItem(
            name = "Home",
            route = "home",
            icon = Icons.Default.Home
        ),
        BottomNavItem(
            name = "Associations",
            route = "associations",
            icon = Icons.Default.FormatListBulleted
        )
    )

    Scaffold(
        bottomBar = {

            //val backStackEntry = navController.currentBackStackEntryAsState()
            BottomNavigation(
                backgroundColor = Color.Green,
                contentColor = Color.Red
            ) {

                items.forEach { item ->
                    //     val selected = item.route == backStackEntry.value?.destination?.route

                    BottomNavigationItem(
                        selected = true,
                        onClick = {
                            //navController.navigate(item.route)
                            navigator.navigate(SecondScreenDestination)
                        },
                        selectedContentColor = Color.Blue,
                        unselectedContentColor = Color.Green,
                        icon = {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.name
                                )
                                Text(text = item.name)
                            }
                        }
                    )
                }
            }
        }
    ) {
    }

}

@Destination
@Composable
fun SecondScreen(navigator: DestinationsNavigator) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black),

        ) {
        BasicText(
            style = TextStyle(color = Color.Green, fontSize = 42.sp),
            modifier = Modifier.offset(y = 140.dp),
            text = "Second Screen and we should see scaffold, but is gone when I navigate use compose destination"
        )
    }

}


data class BottomNavItem(
    val name: String,
    val route: String,
    val icon: ImageVector
)

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

        FirstScreen(EmptyDestinationsNavigator)

}