package com.plcoding.composepaging3caching.ui.common


interface NavigationDestination {
    val route: String
}

sealed class Screen(override val route: String) : NavigationDestination {

    object LoginScreen : Screen("login")
    object RegisterScreen : Screen("register")
    object ForgotPassScreen : Screen("forgot_password")
    object HomeScreen : Screen("home")

}