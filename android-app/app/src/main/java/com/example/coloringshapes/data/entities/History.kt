package com.example.coloringshapes.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "history",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Task::class,
            parentColumns = ["id"],
            childColumns = ["taskId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        androidx.room.Index(value = ["userId"]),
        androidx.room.Index(value = ["taskId"])
    ]
)
data class History(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: Long,
    val taskId: Long,
    val score: Int,
    val completedAt: Long = System.currentTimeMillis(),
    val timeTakenSeconds: Int,
    val status: String = "completed" // completed, time_up, cancelled
)

