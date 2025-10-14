package com.example.coloringshapes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.coloringshapes.data.entities.Task
import com.example.coloringshapes.data.repository.ColoringRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ColoringRepository(application)
    
    val allTasks = repository.getAllTasks()
    
    private val _selectedTask = MutableStateFlow<Task?>(null)
    val selectedTask: StateFlow<Task?> = _selectedTask
    
    fun selectTask(task: Task) {
        _selectedTask.value = task
    }
    
    fun addTask(name: String, description: String, durationMinutes: Int, targetColor: String, shapeType: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                val task = Task(
                    name = name,
                    description = description,
                    durationMinutes = durationMinutes,
                    targetColor = targetColor,
                    shapeType = shapeType
                )
                repository.insertTask(task)
                onSuccess()
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
    
    fun updateTask(task: Task, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                repository.updateTask(task)
                onSuccess()
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
    
    fun deleteTask(task: Task, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                repository.deleteTask(task)
                onSuccess()
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}

