package com.campus.mastermeme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.campus.mastermeme.core.Route
import com.campus.mastermeme.core.presentation.ui.theme.MasterMemeTheme
import com.campus.mastermeme.edit.presentation.EditScreenRoot
import com.campus.mastermeme.edit.presentation.EditViewModel
import com.campus.mastermeme.memes.presentation.MemeListScreenRoot
import com.campus.mastermeme.memes.presentation.MemeListViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            MasterMemeTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Route.MemeGraph
                ) {
                    navigation<Route.MemeGraph>(startDestination = Route.MemeList) {
                        composable<Route.MemeList>(
                            exitTransition = { slideOutHorizontally() },
                            popEnterTransition = {
                                slideInHorizontally()
                            }
                        ) {
                            val viewModel = koinViewModel<MemeListViewModel>()
                            MemeListScreenRoot(
                                viewModel = viewModel,
                                onMemeClick = { memeId ->
                                    navController.navigate(Route.EditMeme(memeId))
                                }
                            )
                        }

                        composable<Route.EditMeme>(
                            exitTransition = { slideOutHorizontally() },
                            popEnterTransition = {
                                slideInHorizontally()
                            }
                        ) {
                            val args = it.toRoute<Route.EditMeme>()
                            val viewModel = koinViewModel<EditViewModel>()
                            EditScreenRoot(
                                viewModel = viewModel,
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                memeId = args.memeId
                            )
                        }


                    }
                }

            }
        }
    }
}
