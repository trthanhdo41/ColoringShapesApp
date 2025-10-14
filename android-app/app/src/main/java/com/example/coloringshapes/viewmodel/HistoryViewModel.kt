package com.example.coloringshapes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.coloringshapes.data.entities.History
import com.example.coloringshapes.data.repository.ColoringRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HistoryViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ColoringRepository(application)
    
    private val _currentUserId = MutableStateFlow<Long?>(null)
    val currentUserId: StateFlow<Long?> = _currentUserId
    
    fun setCurrentUser(userId: Long) {
        _currentUserId.value = userId
    }
    
    fun getUserHistory(userId: Long) = repository.getUserHistory(userId)
    
    fun getTopScores(limit: Int = 10) = repository.getTopScoreHistory(limit)
    
    fun saveHistory(userId: Long, taskId: Long, score: Int, timeTakenSeconds: Int, status: String = "completed", onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                val history = History(
                    userId = userId,
                    taskId = taskId,
                    score = score,
                    timeTakenSeconds = timeTakenSeconds,
                    status = status
                )
                repository.insertHistory(history)
                onSuccess()
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}

