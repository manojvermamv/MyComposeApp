package com.plcoding.composepaging3caching.screens.sigin

import android.content.Context
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(@ApplicationContext context: Context) : ViewModel() {

    fun signIn(email: String, password: String) {
        if (email.isBlank()) {
            showToast("Please enter valid email")
        } else if (password.isBlank()) {
            showToast("Please enter valid password")
        } else {
            showToast("\nEmail: $email \n Password: $password")
        }
    }

    private fun showToast(msg: String) {
        println("AuthViewModel: $msg")
    }

}