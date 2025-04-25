package com.app.exibhitease.presentation.navigation

import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.app.exibhitease.presentation.ai.GenerateImage
import com.app.exibhitease.presentation.ar_screen.ArScreen
import com.app.exibhitease.presentation.data.repository.Art
import com.app.exibhitease.presentation.home_Screen.CustomImageViewModel
import com.app.exibhitease.presentation.home_Screen.FavouriteScreen
import com.app.exibhitease.presentation.home_Screen.HomeScreen
import com.app.exibhitease.presentation.home_Screen.ImageScreen
import com.app.exibhitease.presentation.navigation.Route.CustomImage
import com.app.exibhitease.presentation.onboarding_screen.OnBoardingScreen
import com.app.exibhitease.presentation.settings_screen.SettingsEvent
import com.app.exibhitease.presentation.settings_screen.SettingsViewModel
import com.app.exibhitease.presentation.viewmodel.SharedViewModel
import com.app.exibhitease.utils.AuthManager
import com.google.firebase.auth.FirebaseUser

@Composable
fun NavGraph(
    firstLaunch : Boolean,
    context : Context,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String,
    finishActivity: () -> Unit,
    settingsViewModel: SettingsViewModel
) {

    val authManager : AuthManager = AuthManager(context)

    val user : FirebaseUser? = authManager.getCurrentUser()

    val actions = remember(navController) { MainActions(navController) }
    val viewModel: SharedViewModel = viewModel()
    val customImageViewModel: CustomImageViewModel = viewModel()
    val uri = customImageViewModel.selectedArt.collectAsState().value

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {


        composable(route = Route.OnBoardingScreen.route) { backStackEntry: NavBackStackEntry ->
            BackHandler {
                finishActivity()
            }
            OnBoardingScreen {
                when (it) {
                    SettingsEvent.SetFirstLaunch -> settingsViewModel.onEvent(it)
                    else -> Unit
                }
                actions.onBoardingComplete(backStackEntry)
            }

        }

        navigation(
            route = Route.HomeScreen.route,
            startDestination = ExibhiteaseTabs.Home.route
        ) {
            composable(route = ExibhiteaseTabs.Home.route) {
                BackHandler {
                    finishActivity()
                }
                HomeScreen(
                    firstLaunch = firstLaunch,
                    onclick = {
                        viewModel.selectedArt(it)
                        navController.navigate(Route.ArScreen.route)
                    },
                    onGenerateClick = {
                        navController.navigate(Route.GenerateImage.route)
                    }
                )
            }
            composable(route = ExibhiteaseTabs.Favourites.route) {
                FavouriteScreen()
            }
        }

        composable(route = Route.ArScreen.route) {
            ArScreen(
                viewModel = viewModel
            )
        }

        composable(route = Route.CustomImage.route) {
            ImageScreen(
                imageUri = uri
            ) {
                viewModel.selectedArt(
                    Art(
                        140,
                        "Custom Image",
                        "Custom Image",
                        "5",
                        "street",
                        uri = uri
                    )
                )
                navController.navigate(Route.ArScreen.route)
            }

        }

        composable(route = Route.GenerateImage.route){
            GenerateImage(
                auth = authManager,
                user
            )
        }

    }


}

class MainActions(navController: NavController) {
    val onBoardingComplete: (NavBackStackEntry) -> Unit = { from ->
        if (from.lifecycleIsResumed()) {
            navController.navigate(Route.HomeScreen.route)
        }
    }
}

private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED