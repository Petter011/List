package com.example.testtest.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.testtest.Screens.DetailScreen
import com.example.testtest.Screens.DrinkScreen
import com.example.testtest.Screens.ListScreen
import com.example.testtest.Screens.ListViewModel
import com.example.testtest.Screens.StartScreen

object MainDestinations {
    const val LIST_SCREEN = "ListScreen"
    const val DETAIL_SCREEN = "DetailScreen"
    const val START_SCREEN = "StartScreen"
    const val DRINK_SCREEN = "DrinkScreen"
}

class Actions(navController: NavHostController) {
    val navigateToDetailScreen: (String) -> Unit = { text ->
        navController.navigate("${MainDestinations.DETAIL_SCREEN}/$text")
    }

    val navigateToStartScreen: () -> Unit = {
        navController.navigate(MainDestinations.START_SCREEN) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            popUpTo(navController.graph.startDestinationId) {
                inclusive = false
            }
        }
    }

    val navigateToListScreen: () -> Unit = {
        navController.navigate(MainDestinations.LIST_SCREEN)
    }

    val navigateToDrinkScreen: () -> Unit = {
        navController.navigate(MainDestinations.DRINK_SCREEN)
    }
}

@Composable
fun SetupNavigation() {
    val navController = rememberNavController()
    val listViewModel: ListViewModel = viewModel()

    val actions = remember(navController) { Actions(navController) }

    NavHost(navController, startDestination = MainDestinations.START_SCREEN) {

        composable(MainDestinations.START_SCREEN) {
            StartScreen(actions.navigateToListScreen, actions.navigateToDrinkScreen)
        }

        composable(MainDestinations.LIST_SCREEN) {
            ListScreen(actions.navigateToDetailScreen, listViewModel)
        }

        composable(MainDestinations.DRINK_SCREEN){
            DrinkScreen()
        }

        composable(
            "${MainDestinations.DETAIL_SCREEN}/{text}",
            arguments = listOf(navArgument("text") { type = NavType.StringType })
        ) { backStackEntry ->
            val text = backStackEntry.arguments?.getString("text")
            text?.let {
                DetailScreen(navController, it) { editedText ->
                    // Callback to handle edited text and update list
                    listViewModel.updateItem(text, editedText)
                    navController.popBackStack()
                }
            }
        }
    }
}





