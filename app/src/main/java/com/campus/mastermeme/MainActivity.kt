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
import com.campus.mastermeme.core.Route
import com.campus.mastermeme.core.presentation.ui.theme.MasterMemeTheme
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
                            MemeListScreenRoot(viewModel = viewModel,
                                onMemeClick = { meme ->
                                    navController.navigate(Route.MemeDetail(meme.id))
                                }
                            )
                        }
                        composable<Route.MemeDetail>(
                            enterTransition = {
                                slideInHorizontally { initialOffset ->
                                    initialOffset
                                }
                            },
                            exitTransition = {
                                slideOutHorizontally { initialOffset ->
                                    initialOffset
                                }
                            }
                        ) {

                        }

                    }
                }

            }
        }
    }
}
