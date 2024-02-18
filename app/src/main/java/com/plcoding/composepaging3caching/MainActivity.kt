package com.plcoding.composepaging3caching

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.plcoding.composepaging3caching.screens.EmptyScreen
import com.plcoding.composepaging3caching.screens.sigin.AuthViewModel
import com.plcoding.composepaging3caching.screens.beerhome.BeerScreen
import com.plcoding.composepaging3caching.screens.beerhome.BeerViewModel
import com.plcoding.composepaging3caching.screens.sigin.SignInScreen
import com.plcoding.composepaging3caching.screens.SplashScreen
import com.plcoding.composepaging3caching.screens.splash.SplashViewModel
import com.plcoding.composepaging3caching.ui.theme.Material3ComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current

            Material3ComposeTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "splash") {
                    composable("splash") {
                        val viewModel: SplashViewModel = viewModel()
                        val valid by viewModel.valid.collectAsState()
                        SplashScreen(
                            modifier = Modifier.background(color = MaterialTheme.colors.background),
                            valid = valid,
                            onStart = viewModel::trackSplashScreenStarted,
                            onSplashEndedValid = {
                                navController.navigate("main") {
                                    popUpTo("splash") { inclusive = true }
                                }
                            },
                            onSplashEndedInvalid = {
                                Toast.makeText(context, "Something went wrong..", Toast.LENGTH_LONG).show()
                                navController.navigate("auth") {
                                    popUpTo("splash") { inclusive = true }
                                }
                            }
                        )
                    }
                    composable("about") {
                        EmptyScreen(content = "About Us Screen")
                    }
                    navigation(
                        startDestination = "login",
                        route = "auth"
                    ) {
                        composable("login") {
                            val viewModel = it.sharedHiltViewModel<AuthViewModel>(navController)
                            SignInScreen(viewModel, navController)
                        }
                        composable("register") {
                            val viewModel = it.sharedHiltViewModel<AuthViewModel>(navController)
                            EmptyScreen(content = "Register Screen")
                        }
                        composable("forgot_password") {
                            val viewModel = it.sharedHiltViewModel<AuthViewModel>(navController)
                            EmptyScreen(content = "Forgot Password Screen")
                        }
                    }
                    navigation(
                        startDestination = "home",
                        route = "main"
                    ) {
                        composable("home") {
                            val viewModel = it.sharedHiltViewModel<BeerViewModel>(navController)
                            val beers = viewModel.beerPagingFlow.collectAsLazyPagingItems()
                            Surface(
                                modifier = Modifier.fillMaxSize(),
                                color = MaterialTheme.colors.background
                            ) {
                                BeerScreen(beers = beers)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedHiltViewModel(navController: NavController): T {
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return hiltViewModel(parentEntry)
}