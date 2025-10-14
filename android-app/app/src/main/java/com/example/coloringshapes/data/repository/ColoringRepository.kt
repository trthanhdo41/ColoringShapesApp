package com.example.coloringshapes.data.repository

import android.content.Context
import com.example.coloringshapes.data.AppDatabase
import com.example.coloringshapes.data.entities.*
import kotlinx.coroutines.flow.Flow

class ColoringRepository(context: Context) {
    private val database = AppDatabase.getInstance(context)
    private val userDao = database.userDao()
    private val taskDao = database.taskDao()
    private val shapeDao = database.shapeDao()
    private val historyDao = database.historyDao()
    
    // User operations
    fun getAllUsers(): Flow<List<User>> = userDao.getAllUsers()
    suspend fun getUserById(userId: Long) = userDao.getUserById(userId)
    suspend fun getUserByEmail(email: String) = userDao.getUserByEmail(email)
    suspend fun login(email: String, password: String) = userDao.login(email, password)
    suspend fun register(user: User) = userDao.insertUser(user)
    suspend fun updateUser(user: User) = userDao.updateUser(user)
    
    // Task operations
    fun getAllTasks(): Flow<List<Task>> = taskDao.getAllTasks()
    suspend fun getTaskById(taskId: Long) = taskDao.getTaskById(taskId)
    suspend fun insertTask(task: Task) = taskDao.insertTask(task)
    suspend fun updateTask(task: Task) = taskDao.updateTask(task)
    suspend fun deleteTask(task: Task) = taskDao.deleteTask(task)
    
    // Shape operations
    fun getAllShapes(): Flow<List<Shape>> = shapeDao.getAllShapes()
    suspend fun getShapeById(shapeId: Long) = shapeDao.getShapeById(shapeId)
    suspend fun insertShape(shape: Shape) = shapeDao.insertShape(shape)
    suspend fun updateShape(shape: Shape) = shapeDao.updateShape(shape)
    suspend fun deleteShape(shape: Shape) = shapeDao.deleteShape(shape)
    
    // History operations
    fun getUserHistory(userId: Long): Flow<List<History>> = historyDao.getUserHistory(userId)
    fun getAllHistory(): Flow<List<History>> = historyDao.getAllHistory()
    fun getTopScoreHistory(limit: Int = 10) = historyDao.getTopScoreHistory(limit)
    suspend fun insertHistory(history: History) = historyDao.insertHistory(history)
    
    // Initialize sample data
    suspend fun initializeSampleData() {
        // Add admin user
        try {
            if (userDao.getUserByEmail("admin@gmail.com") == null) {
                userDao.insertUser(
                    User(
                        fullName = "Admin",
                        email = "admin@gmail.com",
                        password = "admin123",
                        birthYear = 1990,
                        gender = "Nam",
                        isAdmin = true
                    )
                )
            }
            
            // Add sample user
            if (userDao.getUserByEmail("user@gmail.com") == null) {
                userDao.insertUser(
                    User(
                        fullName = "Nguyễn Văn A",
                        email = "user@gmail.com",
                        password = "123456",
                        birthYear = 1995,
                        gender = "Nam"
                    )
                )
            }
            
            // Add sample tasks
            taskDao.insertTask(
                Task(
                    name = "Tô màu hình tam giác",
                    description = "Tô màu tam giác với màu đỏ",
                    durationMinutes = 10,
                    targetColor = "#FF6B6B",
                    shapeType = "triangle"
                )
            )
            
            taskDao.insertTask(
                Task(
                    name = "Tô màu hình chữ nhật",
                    description = "Tô màu chữ nhật với màu xanh",
                    durationMinutes = 15,
                    targetColor = "#4F8CFF",
                    shapeType = "rectangle"
                )
            )
            
            taskDao.insertTask(
                Task(
                    name = "Tô màu hình tròn",
                    description = "Tô màu hình tròn với màu vàng",
                    durationMinutes = 8,
                    targetColor = "#F59E0B",
                    shapeType = "circle"
                )
            )
            
            // Add sample shapes
            shapeDao.insertShape(Shape(name = "Tam giác", description = "Hình có 3 cạnh", shapeType = "triangle"))
            shapeDao.insertShape(Shape(name = "Chữ nhật", description = "Hình có 4 góc vuông", shapeType = "rectangle"))
            shapeDao.insertShape(Shape(name = "Hình tròn", description = "Hình tròn hoàn hảo", shapeType = "circle"))
            shapeDao.insertShape(Shape(name = "Hình vuông", description = "Hình có 4 cạnh bằng nhau", shapeType = "square"))
            shapeDao.insertShape(Shape(name = "Hình thoi", description = "Hình có 4 cạnh bằng nhau", shapeType = "diamond"))
            
        } catch (e: Exception) {
            // Data already exists
        }
    }
}

