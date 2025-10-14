package com.example.coloringshapes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.coloringshapes.data.entities.Shape
import com.example.coloringshapes.data.repository.ColoringRepository
import kotlinx.coroutines.launch

class ShapeViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ColoringRepository(application)
    
    val allShapes = repository.getAllShapes()
    
    fun addShape(name: String, description: String, shapeType: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                val shape = Shape(
                    name = name,
                    description = description,
                    shapeType = shapeType
                )
                repository.insertShape(shape)
                onSuccess()
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
    
    fun updateShape(shape: Shape, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                repository.updateShape(shape)
                onSuccess()
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
    
    fun deleteShape(shape: Shape, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                repository.deleteShape(shape)
                onSuccess()
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}

