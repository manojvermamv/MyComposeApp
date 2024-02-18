package com.plcoding.composepaging3caching.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random

class SplashViewModel : ViewModel() {

    private val _valid: MutableStateFlow<Boolean?> = MutableStateFlow(null)
    val valid: StateFlow<Boolean?> = _valid

    init {
        isUserDataValid()
    }

    private fun isUserDataValid() = viewModelScope.launch {
        delay(1000)
        // check logged in user valid or not
        _valid.update { Random.nextBoolean() }
    }

    fun trackSplashScreenStarted() {
        //dummy method
    }
}