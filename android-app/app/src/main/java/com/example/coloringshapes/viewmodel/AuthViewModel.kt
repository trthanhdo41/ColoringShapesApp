package com.example.coloringshapes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.coloringshapes.data.entities.User
import com.example.coloringshapes.data.repository.ColoringRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ColoringRepository(application)
    
    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser
    
    private val _loginError = MutableStateFlow<String?>(null)
    val loginError: StateFlow<String?> = _loginError
    
    private val _registerError = MutableStateFlow<String?>(null)
    val registerError: StateFlow<String?> = _registerError
    
    fun login(email: String, password: String, onSuccess: (User) -> Unit) {
        viewModelScope.launch {
            try {
                val user = repository.login(email, password)
                if (user != null) {
                    _currentUser.value = user
                    _loginError.value = null
                    onSuccess(user)
                } else {
                    _loginError.value = "Email hoặc mật khẩu không đúng"
                }
            } catch (e: Exception) {
                _loginError.value = "Lỗi: ${e.message}"
            }
        }
    }
    
    fun register(fullName: String, email: String, password: String, birthYear: Int, gender: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                // Check if email exists
                val existingUser = repository.getUserByEmail(email)
                if (existingUser != null) {
                    _registerError.value = "Email đã tồn tại"
                    return@launch
                }
                
                // Create new user
                val user = User(
                    fullName = fullName,
                    email = email,
                    password = password,
                    birthYear = birthYear,
                    gender = gender
                )
                repository.register(user)
                _registerError.value = null
                onSuccess()
            } catch (e: Exception) {
                _registerError.value = "Lỗi: ${e.message}"
            }
        }
    }
    
    fun logout() {
        _currentUser.value = null
    }
    
    fun clearErrors() {
        _loginError.value = null
        _registerError.value = null
    }
}

