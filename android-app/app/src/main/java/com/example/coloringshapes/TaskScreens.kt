package com.example.coloringshapes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coloringshapes.data.entities.Task
import com.example.coloringshapes.viewmodel.TaskViewModel

@Composable
fun ScreenTasksWithDB(
    taskViewModel: TaskViewModel = viewModel(),
    onOpenDetail: (Task) -> Unit,
    onLogout: () -> Unit
) {
    val tasks by taskViewModel.allTasks.collectAsState(initial = emptyList())
    
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Nhiệm vụ",
                style = MaterialTheme.typography.headlineLarge
            )
            IconButton(onClick = onLogout) {
                Icon(Icons.AutoMirrored.Filled.Logout, contentDescription = "Đăng xuất")
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        if (tasks.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Chưa có nhiệm vụ nào")
            }
        } else {
            LazyColumn {
                items(tasks) { task ->
                    TaskCardDB(
                        task = task,
                        onStart = { onOpenDetail(task) }
                    )
                }
            }
        }
    }
}

@Composable
fun TaskCardDB(task: Task, onStart: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = task.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(
                            color = Color(task.targetColor.toColorInt()),
                            shape = CircleShape
                        )
                )
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = task.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Thời gian: ${task.durationMinutes} phút",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "Hình: ${task.shapeType}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Button(
                onClick = onStart,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(task.targetColor.toColorInt())
                )
            ) {
                Text(
                    text = "Bắt đầu",
                    color = Color.White
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenAdminTaskEditorWithDB(
    taskViewModel: TaskViewModel = viewModel(),
    onSave: () -> Unit,
    onCancel: () -> Unit
) {
    var taskName by remember { mutableStateOf("") }
    var taskDescription by remember { mutableStateOf("") }
    var maxDuration by remember { mutableStateOf("") }
    var taskColor by remember { mutableStateOf("#FF6B6B") }
    var shapeType by remember { mutableStateOf("triangle") }
    var expanded by remember { mutableStateOf(false) }
    
    val shapeTypes = listOf("triangle", "rectangle", "circle", "square", "diamond")
    val shapeTypeNames = listOf("Tam giác", "Chữ nhật", "Hình tròn", "Hình vuông", "Hình thoi")
    
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onCancel) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
            Text(
                text = "Thêm nhiệm vụ",
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.width(48.dp))
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                OutlinedTextField(
                    value = taskName,
                    onValueChange = { taskName = it },
                    label = { Text("Tên nhiệm vụ") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                OutlinedTextField(
                    value = taskDescription,
                    onValueChange = { taskDescription = it },
                    label = { Text("Mô tả") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 3
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                OutlinedTextField(
                    value = maxDuration,
                    onValueChange = { maxDuration = it },
                    label = { Text("Thời gian (phút)") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = taskColor,
                        onValueChange = { taskColor = it },
                        label = { Text("Màu (Hex)") },
                        modifier = Modifier.weight(1f),
                        placeholder = { Text("#FF6B6B") },
                        singleLine = true
                    )
                    
                    Spacer(modifier = Modifier.width(16.dp))
                    
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(
                                color = try { Color(taskColor.toColorInt()) } catch (e: Exception) { Color.Gray },
                                shape = CircleShape
                            )
                    )
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = it }
                ) {
                    OutlinedTextField(
                        value = shapeTypeNames.getOrNull(shapeTypes.indexOf(shapeType)) ?: "",
                        onValueChange = { },
                        readOnly = true,
                        label = { Text("Loại hình") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor()
                    )
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        shapeTypes.forEachIndexed { index, type ->
                            DropdownMenuItem(
                                text = { Text(shapeTypeNames[index]) },
                                onClick = { 
                                    shapeType = type
                                    expanded = false 
                                }
                            )
                        }
                    }
                }
            }
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = onCancel,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.outline
                )
            ) {
                Text("Hủy")
            }
            Button(
                onClick = {
                    val duration = maxDuration.toIntOrNull() ?: 0
                    if (taskName.isNotBlank() && duration > 0) {
                        taskViewModel.addTask(
                            name = taskName,
                            description = taskDescription,
                            durationMinutes = duration,
                            targetColor = taskColor,
                            shapeType = shapeType
                        ) {
                            onSave()
                        }
                    }
                },
                modifier = Modifier.weight(1f),
                enabled = taskName.isNotBlank() && maxDuration.toIntOrNull() != null
            ) {
                Text("Lưu")
            }
        }
    }
}

