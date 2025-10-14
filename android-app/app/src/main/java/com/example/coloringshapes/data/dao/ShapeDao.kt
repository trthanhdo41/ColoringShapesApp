package com.example.coloringshapes.data.dao

import androidx.room.*
import com.example.coloringshapes.data.entities.Shape
import kotlinx.coroutines.flow.Flow

@Dao
interface ShapeDao {
    @Query("SELECT * FROM shapes ORDER BY createdAt DESC")
    fun getAllShapes(): Flow<List<Shape>>
    
    @Query("SELECT * FROM shapes WHERE id = :shapeId")
    suspend fun getShapeById(shapeId: Long): Shape?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShape(shape: Shape): Long
    
    @Update
    suspend fun updateShape(shape: Shape)
    
    @Delete
    suspend fun deleteShape(shape: Shape)
    
    @Query("DELETE FROM shapes WHERE id = :shapeId")
    suspend fun deleteShapeById(shapeId: Long)
}

