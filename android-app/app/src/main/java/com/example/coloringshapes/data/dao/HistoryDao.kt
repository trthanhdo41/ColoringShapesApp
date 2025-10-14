package com.example.coloringshapes.data.dao

import androidx.room.*
import com.example.coloringshapes.data.entities.History
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {
    @Query("SELECT * FROM history WHERE userId = :userId ORDER BY completedAt DESC")
    fun getUserHistory(userId: Long): Flow<List<History>>
    
    @Query("SELECT * FROM history ORDER BY completedAt DESC")
    fun getAllHistory(): Flow<List<History>>
    
    @Query("SELECT * FROM history ORDER BY score DESC LIMIT :limit")
    fun getTopScoreHistory(limit: Int): Flow<List<History>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistory(history: History): Long
    
    @Delete
    suspend fun deleteHistory(history: History)
}

