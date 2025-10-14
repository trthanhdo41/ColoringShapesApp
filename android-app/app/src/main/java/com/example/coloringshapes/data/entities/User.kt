package com.example.coloringshapes.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val fullName: String,
    val email: String,
    val password: String,
    val birthYear: Int,
    val gender: String,
    val isAdmin: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)

