package com.example.coloringshapes.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shapes")
data class Shape(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val description: String,
    val shapeType: String, // triangle, rectangle, circle, square, diamond
    val createdAt: Long = System.currentTimeMillis()
)

