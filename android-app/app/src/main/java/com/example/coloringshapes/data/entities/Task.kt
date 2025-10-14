package com.example.coloringshapes.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val description: String,
    val durationMinutes: Int,
    val targetColor: String, // Hex color code
    val shapeType: String, // triangle, rectangle, circle, etc.
    val createdAt: Long = System.currentTimeMillis()
)

