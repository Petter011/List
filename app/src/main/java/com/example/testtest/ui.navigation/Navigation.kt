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
import com.example.testtest.Screens.ListScreen
import com.example.testtest.Screens.ListViewModel

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
    val listViewModel: ListViewModel = viewModel()

    val actions = remember(navController) { Actions(navController) }

    NavHost(navController, startDestination = MainDestinations.LIST_SCREEN) {
        composable(MainDestinations.LIST_SCREEN) {
            ListScreen(actions.navigateToDetailScreen, listViewModel)
        }
        composable(
            "${MainDestinations.DETAIL_SCREEN}/{text}",
            arguments = listOf(navArgument("text") { type = NavType.StringType })
        ) { backStackEntry ->
            val text = backStackEntry.arguments?.getString("text")
            text?.let { DetailScreen(navController, it){ editedText ->
                // Callback to handle edited text and update list
                listViewModel.updateItem(text, editedText)
                navController.popBackStack()
            } }
        }
    }
}


