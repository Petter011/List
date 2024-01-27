package com.example.testtest.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.testtest.site.DetailScreen
import com.example.testtest.site.ListScreen

object MainDestinations {
    const val LIST_SCREEN = "ListScreen"
    const val DETAIL_SCREEN = "DetailScreen"
}

class Actions(navController: NavHostController) {
    val navigateToDetailScreen: (String) -> Unit = { text ->
        navController.navigate("${MainDestinations.DETAIL_SCREEN}/$text")
    }
}

@Composable
fun SetupNavigation() {
    val navController = rememberNavController()

    val actions = remember(navController) { Actions(navController) }

    NavHost(navController, startDestination = MainDestinations.LIST_SCREEN) {
        composable(MainDestinations.LIST_SCREEN) {
            ListScreen(actions.navigateToDetailScreen)
        }
        composable(
            "${MainDestinations.DETAIL_SCREEN}/{text}",
            arguments = listOf(navArgument("text") { type = NavType.StringType })
        ) { backStackEntry ->
            val text = backStackEntry.arguments?.getString("text")
            text?.let { DetailScreen(navController, it) }
        }
    }
}


