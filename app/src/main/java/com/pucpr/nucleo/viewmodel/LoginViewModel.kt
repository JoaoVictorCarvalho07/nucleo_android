package com.pucpr.nucleo.viewmodel

import androidx.lifecycle.ViewModel
import com.pucpr.nucleo.model.LoginRequest

class LoginViewModel : ViewModel() {

    fun login(email: String, password: String, onSuccess: () -> Unit) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            onSuccess()
        }
    }
}