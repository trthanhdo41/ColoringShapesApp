package com.example.coloringshapes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.coloringshapes.data.repository.ColoringRepository

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ColoringRepository(application)
    
    val allUsers = repository.getAllUsers()
}

