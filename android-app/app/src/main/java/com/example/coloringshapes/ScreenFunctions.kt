package com.example.coloringshapes

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenLogin(onLoggedIn: () -> Unit, onRegister: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    
    Surface(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        Icons.Default.Palette,
                        contentDescription = null,
                        modifier = Modifier.size(64.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Text(
                        text = stringResource(R.string.login),
                        style = MaterialTheme.typography.headlineLarge
                    )
                    
                    Spacer(modifier = Modifier.height(32.dp))
                    
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text(stringResource(R.string.email)) },
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                        singleLine = true
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text(stringResource(R.string.password)) },
                        modifier = Modifier.fillMaxWidth(),
                        visualTransformation = PasswordVisualTransformation(),
                        leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                        singleLine = true
                    )
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    Button(
                        onClick = onLoggedIn,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(stringResource(R.string.login))
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    TextButton(onClick = onRegister) {
                        Text(stringResource(R.string.register))
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenRegister(onRegistered: () -> Unit, onLogin: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var fullName by remember { mutableStateOf("") }
    var birthYear by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    
    Surface(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        Icons.Default.PersonAdd,
                        contentDescription = null,
                        modifier = Modifier.size(64.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Text(
                        text = stringResource(R.string.register),
                        style = MaterialTheme.typography.headlineLarge
                    )
                    
                    Spacer(modifier = Modifier.height(32.dp))
                    
                    OutlinedTextField(
                        value = fullName,
                        onValueChange = { fullName = it },
                        label = { Text(stringResource(R.string.full_name)) },
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                        singleLine = true
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text(stringResource(R.string.email)) },
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                        singleLine = true
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text(stringResource(R.string.password)) },
                        modifier = Modifier.fillMaxWidth(),
                        visualTransformation = PasswordVisualTransformation(),
                        leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                        singleLine = true
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    OutlinedTextField(
                        value = birthYear,
                        onValueChange = { birthYear = it },
                        label = { Text(stringResource(R.string.birth_year)) },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        leadingIcon = { Icon(Icons.Default.CalendarToday, contentDescription = null) },
                        singleLine = true
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Box {
                        OutlinedTextField(
                            value = gender,
                            onValueChange = { },
                            readOnly = true,
                            label = { Text(stringResource(R.string.gender)) },
                            trailingIcon = { 
                                Icon(
                                    if (expanded) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                                    contentDescription = null
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { expanded = !expanded }
                        )
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text(stringResource(R.string.male)) },
                                onClick = { gender = "Nam"; expanded = false }
                            )
                            DropdownMenuItem(
                                text = { Text(stringResource(R.string.female)) },
                                onClick = { gender = "Nữ"; expanded = false }
                            )
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    Button(
                        onClick = onRegistered,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(stringResource(R.string.register))
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    TextButton(onClick = onLogin) {
                        Text(stringResource(R.string.login))
                    }
                }
            }
        }
    }
}

@Composable
fun ScreenTasks(onOpenDetail: () -> Unit, onLogout: () -> Unit) {
    val tasks = listOf(
        TaskItem("Tô màu hình tam giác", "Tô màu tam giác với màu đỏ", "10 phút", "#FF6B6B", "triangle"),
        TaskItem("Tô màu hình chữ nhật", "Tô màu chữ nhật với màu xanh", "15 phút", "#4F8CFF", "rectangle"),
        TaskItem("Tô màu hình tròn", "Tô màu hình tròn với màu vàng", "8 phút", "#F59E0B", "circle")
    )
    
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.tasks),
                style = MaterialTheme.typography.headlineLarge
            )
            IconButton(onClick = onLogout) {
                Icon(Icons.AutoMirrored.Filled.Logout, contentDescription = stringResource(R.string.logout))
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        LazyColumn {
            items(tasks) { task ->
                TaskCard(
                    task = task,
                    onStart = onOpenDetail
                )
            }
        }
    }
}

data class TaskItem(
    val name: String,
    val description: String,
    val duration: String,
    val color: String,
    val shapeType: String
)

@Composable
fun TaskCard(task: TaskItem, onStart: () -> Unit) {
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
                            color = Color(task.color.toColorInt()),
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
                    text = "${stringResource(R.string.max_duration)}: ${task.duration}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "Hình: ${getShapeName(task.shapeType)}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Button(
                onClick = onStart,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(task.color.toColorInt())
                )
            ) {
                Text(
                    text = stringResource(R.string.start_task),
                    color = Color.White
                )
            }
        }
    }
}

private fun getShapeName(shapeType: String): String {
    return when (shapeType) {
        "triangle" -> "Tam giác"
        "rectangle" -> "Chữ nhật"
        "circle" -> "Hình tròn"
        else -> "Không xác định"
    }
}

@Composable
fun ScreenHistory(onLogout: () -> Unit) {
    val history = listOf(
        HistoryItem("Tô màu hình tam giác", "95 điểm", "Hoàn thành", "#FF6B6B", "2024-01-15"),
        HistoryItem("Tô màu hình vuông", "87 điểm", "Hoàn thành", "#4F8CFF", "2024-01-14"),
        HistoryItem("Tô màu hình tròn", "92 điểm", "Hoàn thành", "#F59E0B", "2024-01-13")
    )
    
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.history),
                style = MaterialTheme.typography.headlineLarge
            )
            IconButton(onClick = onLogout) {
                Icon(Icons.AutoMirrored.Filled.Logout, contentDescription = stringResource(R.string.logout))
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        LazyColumn {
            items(history) { item ->
                HistoryCard(item = item)
            }
        }
    }
}

data class HistoryItem(
    val taskName: String,
    val score: String,
    val status: String,
    val color: String,
    val date: String
)

@Composable
fun HistoryCard(item: HistoryItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(
                            color = Color(item.color.toColorInt()),
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = item.score.split(" ")[0],
                        color = Color.White,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                
                Spacer(modifier = Modifier.width(16.dp))
                
                Column {
                    Text(
                        text = item.taskName,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = item.date,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = item.score,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = item.status,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun ScreenTaskDetail(onStart: () -> Unit, onBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
            Text(
                text = stringResource(R.string.task_detail),
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.width(48.dp))
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = "Tô màu hình tam giác",
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Tô màu tam giác với màu đỏ theo yêu cầu",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "${stringResource(R.string.max_duration)}: 10 ${stringResource(R.string.minutes)}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "${stringResource(R.string.task_color)}: #FF6B6B",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = onStart,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(stringResource(R.string.start_task))
                }
            }
        }
    }
}

@Composable
fun ScreenColoring(onComplete: () -> Unit, onBack: () -> Unit) {
    var selectedColor by remember { mutableStateOf(Color(0xFF4F8CFF)) }
    var strokes by remember { mutableStateOf(listOf<List<Offset>>()) }
    var currentStroke by remember { mutableStateOf(listOf<Offset>()) }
    var timeRemaining by remember { mutableIntStateOf(600) }
    var isActive by remember { mutableStateOf(true) }
    var showTimeUp by remember { mutableStateOf(false) }
    var shapeType by remember { mutableStateOf("triangle") }
    
    LaunchedEffect(isActive) {
        while (isActive && timeRemaining > 0) {
            delay(1000)
            timeRemaining--
        }
        if (timeRemaining <= 0) {
            showTimeUp = true
        }
    }
    
    if (showTimeUp) {
        TimeUpDialog(
            onDismiss = { showTimeUp = false },
            onComplete = onComplete
        )
    } else {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
                Text(
                    text = stringResource(R.string.coloring),
                    style = MaterialTheme.typography.headlineLarge
                )
                Spacer(modifier = Modifier.width(48.dp))
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Card(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${stringResource(R.string.time_remaining)}: ${formatTime(timeRemaining)}",
                        style = MaterialTheme.typography.titleMedium,
                        color = if (timeRemaining < 60) Color.Red else MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "${stringResource(R.string.score)}: ${calculateScore(strokes)}",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Canvas(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .pointerInput(Unit) {
                            detectDragGestures(
                                onDragStart = { offset ->
                                    currentStroke = listOf(offset)
                                },
                                onDragEnd = {
                                    strokes = strokes + listOf(currentStroke)
                                    currentStroke = emptyList()
                                },
                                onDragCancel = {
                                    currentStroke = emptyList()
                                }
                            ) { change, _ ->
                                change.consume()
                                currentStroke = currentStroke + listOf(change.position)
                            }
                        }
                ) {
                    val centerX = size.width / 2
                    val centerY = size.height / 2
                    val shapeSize = minOf(size.width, size.height) * 0.3f
                    
                    val shapePath = Path()
                    
                    when (shapeType) {
                        "triangle" -> {
                            val top = Offset(centerX, centerY - shapeSize)
                            val left = Offset(centerX - shapeSize * 0.866f, centerY + shapeSize * 0.5f)
                            val right = Offset(centerX + shapeSize * 0.866f, centerY + shapeSize * 0.5f)
                            
                            shapePath.moveTo(top.x, top.y)
                            shapePath.lineTo(left.x, left.y)
                            shapePath.lineTo(right.x, right.y)
                            shapePath.close()
                        }
                        "rectangle" -> {
                            val left = centerX - shapeSize
                            val top = centerY - shapeSize * 0.6f
                            val right = centerX + shapeSize
                            val bottom = centerY + shapeSize * 0.6f
                            
                            shapePath.moveTo(left, top)
                            shapePath.lineTo(right, top)
                            shapePath.lineTo(right, bottom)
                            shapePath.lineTo(left, bottom)
                            shapePath.close()
                        }
                        "circle" -> {
                            shapePath.addOval(
                                androidx.compose.ui.geometry.Rect(
                                    centerX - shapeSize,
                                    centerY - shapeSize,
                                    centerX + shapeSize,
                                    centerY + shapeSize
                                )
                            )
                        }
                    }
                    
                    drawPath(
                        shapePath,
                        color = Color.Black,
                        style = Stroke(
                            width = 4f,
                            cap = StrokeCap.Round,
                            join = StrokeJoin.Round
                        )
                    )
                    
                    val path = Path()
                    
                    strokes.forEach { stroke ->
                        if (stroke.isNotEmpty()) {
                            path.reset()
                            path.moveTo(stroke.first().x, stroke.first().y)
                            stroke.drop(1).forEach { point ->
                                path.lineTo(point.x, point.y)
                            }
                            drawPath(
                                path,
                                color = selectedColor,
                                style = Stroke(
                                    width = 8f,
                                    cap = StrokeCap.Round,
                                    join = StrokeJoin.Round
                                )
                            )
                        }
                    }
                    
                    if (currentStroke.isNotEmpty()) {
                        path.reset()
                        path.moveTo(currentStroke.first().x, currentStroke.first().y)
                        currentStroke.drop(1).forEach { point ->
                            path.lineTo(point.x, point.y)
                        }
                        drawPath(
                            path,
                            color = selectedColor,
                            style = Stroke(
                                width = 8f,
                                cap = StrokeCap.Round,
                                join = StrokeJoin.Round
                            )
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Chọn màu:",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        ColorSwatch(Color(0xFF4F8CFF), selectedColor == Color(0xFF4F8CFF)) { selectedColor = it }
                        ColorSwatch(Color(0xFF7B61FF), selectedColor == Color(0xFF7B61FF)) { selectedColor = it }
                        ColorSwatch(Color(0xFFFF6B6B), selectedColor == Color(0xFFFF6B6B)) { selectedColor = it }
                        ColorSwatch(Color(0xFF22C55E), selectedColor == Color(0xFF22C55E)) { selectedColor = it }
                        ColorSwatch(Color(0xFFF59E0B), selectedColor == Color(0xFFF59E0B)) { selectedColor = it }
                        ColorSwatch(Color(0xFF000000), selectedColor == Color(0xFF000000)) { selectedColor = it }
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = { strokes = emptyList() },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.outline
                    )
                ) {
                    Text("Xóa")
                }
                Button(
                    onClick = onComplete,
                    modifier = Modifier.weight(2f)
                ) {
                    Text(stringResource(R.string.complete_task))
                }
            }
        }
    }
}

@Composable
fun ColorSwatch(color: Color, selected: Boolean, onPick: (Color) -> Unit) {
    val size = 48.dp
    
    Box(
        modifier = Modifier
            .size(size)
            .background(
                color = if (selected) MaterialTheme.colorScheme.primary else Color.Transparent,
                shape = CircleShape
            )
            .padding(if (selected) 4.dp else 2.dp)
            .background(
                color = color,
                shape = CircleShape
            )
            .clickable { onPick(color) }
    )
}

@Composable
fun TimeUpDialog(onDismiss: () -> Unit, onComplete: () -> Unit) {
    var animationScale by remember { mutableFloatStateOf(1f) }
    
    LaunchedEffect(Unit) {
        while (true) {
            animationScale = 1.2f
            delay(500)
            animationScale = 1f
            delay(500)
        }
    }
    
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    Icons.Default.Warning,
                    contentDescription = null,
                    tint = Color.Red,
                    modifier = Modifier
                        .size(32.dp)
                        .scale(animationScale)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = stringResource(R.string.time_up),
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.Red
                )
            }
        },
        text = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "⏰ Thời gian đã hết!",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Bạn có muốn nộp bài không?",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        },
        confirmButton = {
            Button(
                onClick = onComplete,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(stringResource(R.string.complete_task))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.cancel))
            }
        }
    )
}

private fun formatTime(seconds: Int): String {
    val minutes = seconds / 60
    val remainingSeconds = seconds % 60
    return String.format(Locale.getDefault(), "%02d:%02d", minutes, remainingSeconds)
}

private fun calculateScore(strokes: List<List<Offset>>): Int {
    return strokes.sumOf { it.size } * 2
}

@Composable
fun ScreenAdmin(nav: NavHostController, onLogout: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.admin),
                style = MaterialTheme.typography.headlineLarge
            )
            IconButton(onClick = onLogout) {
                Icon(Icons.AutoMirrored.Filled.Logout, contentDescription = stringResource(R.string.logout))
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Chào mừng đến với trang quản trị",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Quản lý nhiệm vụ, hình học và người dùng",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        val menuItems = listOf(
            AdminMenuItem(stringResource(R.string.manage_tasks), Routes.ADMIN_TASKS, Icons.AutoMirrored.Filled.Assignment, "Quản lý các nhiệm vụ tô màu"),
            AdminMenuItem(stringResource(R.string.manage_shapes), Routes.ADMIN_SHAPES, Icons.Default.Category, "Quản lý các hình học"),
            AdminMenuItem(stringResource(R.string.user_list), Routes.ADMIN_USERS, Icons.Default.People, "Xem danh sách người dùng"),
            AdminMenuItem(stringResource(R.string.top_scores), Routes.ADMIN_REPORTS, Icons.Default.BarChart, "Xem báo cáo điểm số"),
            AdminMenuItem(stringResource(R.string.user_artworks), Routes.ADMIN_ARTWORKS, Icons.Default.Image, "Xem tác phẩm người dùng")
        )
        
        LazyColumn {
            items(menuItems) { menuItem ->
                AdminMenuCard(
                    menuItem = menuItem,
                    onClick = { nav.navigate(menuItem.route) }
                )
            }
        }
    }
}

data class AdminMenuItem(
    val title: String,
    val route: String,
    val icon: ImageVector,
    val description: String
)

@Composable
fun AdminMenuCard(menuItem: AdminMenuItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        menuItem.icon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
                
                Spacer(modifier = Modifier.width(16.dp))
                
                Column {
                    Text(
                        text = menuItem.title,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = menuItem.description,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            
            Icon(
                Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Go to ${menuItem.title}",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun ScreenAdminTasks(onAdd: () -> Unit, onBack: () -> Unit) {
    val tasks = listOf(
        AdminTaskItem("Tô màu hình tam giác", "Tô màu tam giác với màu đỏ", "10 phút", "Đếm ngược", "#FF6B6B"),
        AdminTaskItem("Tô màu hình chữ nhật", "Tô màu chữ nhật với màu xanh", "15 phút", "Đếm lên", "#4F8CFF"),
        AdminTaskItem("Tô màu hình tròn", "Tô màu hình tròn với màu vàng", "8 phút", "Đếm ngược", "#F59E0B")
    )
    
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
            Text(
                text = stringResource(R.string.manage_tasks),
                style = MaterialTheme.typography.headlineLarge
            )
            IconButton(onClick = onAdd) {
                Icon(Icons.Default.Add, contentDescription = stringResource(R.string.add_task))
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        LazyColumn {
            items(tasks) { task ->
                AdminTaskCard(task = task)
            }
        }
    }
}

data class AdminTaskItem(
    val name: String,
    val description: String,
    val duration: String,
    val timerType: String,
    val color: String
)

@Composable
fun AdminTaskCard(task: AdminTaskItem) {
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
                            color = Color(task.color.toColorInt()),
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
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${stringResource(R.string.max_duration)}: ${task.duration}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "Timer: ${task.timerType}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(stringResource(R.string.edit_task))
                }
                Button(
                    onClick = { },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Text(stringResource(R.string.delete_task))
                }
            }
        }
    }
}

@Composable
fun ScreenAdminShapes(onAdd: () -> Unit, onBack: () -> Unit) {
    val shapes = listOf(
        AdminShapeItem("Tam giác", "Hình có 3 cạnh", "triangle"),
        AdminShapeItem("Chữ nhật", "Hình có 4 góc vuông", "rectangle"),
        AdminShapeItem("Hình tròn", "Hình tròn hoàn hảo", "circle"),
        AdminShapeItem("Hình vuông", "Hình có 4 cạnh bằng nhau", "square"),
        AdminShapeItem("Hình thoi", "Hình có 4 cạnh bằng nhau", "diamond")
    )
    
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
            Text(
                text = stringResource(R.string.manage_shapes),
                style = MaterialTheme.typography.headlineLarge
            )
            IconButton(onClick = onAdd) {
                Icon(Icons.Default.Add, contentDescription = stringResource(R.string.add_shape))
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        LazyColumn {
            items(shapes) { shape ->
                AdminShapeCard(shape = shape)
            }
        }
    }
}

data class AdminShapeItem(
    val name: String,
    val description: String,
    val type: String
)

@Composable
fun AdminShapeCard(shape: AdminShapeItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(
                            color = MaterialTheme.colorScheme.secondaryContainer,
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.Category,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
                
                Spacer(modifier = Modifier.width(16.dp))
                
                Column {
                    Text(
                        text = shape.name,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = shape.description,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            
            Row {
                IconButton(onClick = { }) {
                    Icon(
                        Icons.Default.Edit,
                        contentDescription = stringResource(R.string.edit_shape),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                IconButton(onClick = { }) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = stringResource(R.string.delete_shape),
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}

@Composable
fun ScreenAdminUsers(onBack: () -> Unit) {
    val users = listOf(
        AdminUserItem("Nguyễn Văn A", "nguyenvana@email.com", "Nam", "1995", "95 điểm"),
        AdminUserItem("Trần Thị B", "tranthib@email.com", "Nữ", "1998", "87 điểm"),
        AdminUserItem("Lê Văn C", "levanc@email.com", "Nam", "1992", "92 điểm")
    )
    
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
            Text(
                text = stringResource(R.string.user_list),
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.width(48.dp))
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        LazyColumn {
            items(users) { user ->
                AdminUserCard(user = user)
            }
        }
    }
}

data class AdminUserItem(
    val name: String,
    val email: String,
    val gender: String,
    val birthYear: String,
    val averageScore: String
)

@Composable
fun AdminUserCard(user: AdminUserItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(
                            color = MaterialTheme.colorScheme.tertiaryContainer,
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = user.name.split(" ").last().first().toString(),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                }
                
                Spacer(modifier = Modifier.width(16.dp))
                
                Column {
                    Text(
                        text = user.name,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = user.email,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "${user.gender} - ${user.birthYear}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = user.averageScore,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Điểm TB",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun ScreenAdminReports(onBack: () -> Unit) {
    var selectedFilter by remember { mutableStateOf("top_3") }
    val topScores = listOf(
        AdminScoreItem("Nguyễn Văn A", "98 điểm", 1),
        AdminScoreItem("Trần Thị B", "95 điểm", 2),
        AdminScoreItem("Lê Văn C", "92 điểm", 3),
        AdminScoreItem("Phạm Thị D", "89 điểm", 4),
        AdminScoreItem("Hoàng Văn E", "87 điểm", 5)
    )
    
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
            Text(
                text = stringResource(R.string.top_scores),
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.width(48.dp))
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Lọc theo:",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FilterChip(
                        onClick = { selectedFilter = "top_3" },
                        label = { Text(stringResource(R.string.top_3)) },
                        selected = selectedFilter == "top_3"
                    )
                    FilterChip(
                        onClick = { selectedFilter = "top_10" },
                        label = { Text(stringResource(R.string.top_10)) },
                        selected = selectedFilter == "top_10"
                    )
                    FilterChip(
                        onClick = { selectedFilter = "top_100" },
                        label = { Text(stringResource(R.string.top_100)) },
                        selected = selectedFilter == "top_100"
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        LazyColumn {
            items(topScores) { score ->
                AdminScoreCard(score = score)
            }
        }
    }
}

data class AdminScoreItem(
    val name: String,
    val score: String,
    val rank: Int
)

@Composable
fun AdminScoreCard(score: AdminScoreItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(
                            color = when (score.rank) {
                                1 -> Color(0xFFFFD700)
                                2 -> Color(0xFFC0C0C0)
                                3 -> Color(0xFFCD7F32)
                                else -> MaterialTheme.colorScheme.surfaceVariant
                            },
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = score.rank.toString(),
                        style = MaterialTheme.typography.titleMedium,
                        color = if (score.rank <= 3) Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                
                Spacer(modifier = Modifier.width(16.dp))
                
                Text(
                    text = score.name,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            
            Text(
                text = score.score,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun ScreenAdminArtworks(onBack: () -> Unit) {
    val artworks = listOf(
        AdminArtworkItem("Tác phẩm 1", "Nguyễn Văn A", "Tam giác đỏ", "2024-01-15", "#FF6B6B"),
        AdminArtworkItem("Tác phẩm 2", "Trần Thị B", "Chữ nhật xanh", "2024-01-14", "#4F8CFF"),
        AdminArtworkItem("Tác phẩm 3", "Lê Văn C", "Hình tròn vàng", "2024-01-13", "#F59E0B")
    )
    
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
            Text(
                text = stringResource(R.string.user_artworks),
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.width(48.dp))
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        LazyColumn {
            items(artworks) { artwork ->
                AdminArtworkCard(artwork = artwork)
            }
        }
    }
}

data class AdminArtworkItem(
    val title: String,
    val author: String,
    val description: String,
    val date: String,
    val color: String
)

@Composable
fun AdminArtworkCard(artwork: AdminArtworkItem) {
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
                    text = artwork.title,
                    style = MaterialTheme.typography.titleMedium
                )
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .background(
                            color = Color(artwork.color.toColorInt()),
                            shape = CircleShape
                        )
                )
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = "Tác giả: ${artwork.author}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            Spacer(modifier = Modifier.height(4.dp))
            
            Text(
                text = artwork.description,
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
                    text = "Ngày: ${artwork.date}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Row {
                    IconButton(onClick = { }) {
                        Icon(
                            Icons.Default.Visibility,
                            contentDescription = "Xem chi tiết",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    IconButton(onClick = { }) {
                        Icon(
                            Icons.Default.Download,
                            contentDescription = "Tải xuống",
                            tint = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenAdminTaskEditor(onSave: () -> Unit, onCancel: () -> Unit) {
    var taskName by remember { mutableStateOf("") }
    var taskDescription by remember { mutableStateOf("") }
    var maxDuration by remember { mutableStateOf("") }
    var taskColor by remember { mutableStateOf("#FF6B6B") }
    var isCountdown by remember { mutableStateOf(true) }
    
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
                text = stringResource(R.string.add_task),
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
                    label = { Text(stringResource(R.string.task_name)) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                OutlinedTextField(
                    value = taskDescription,
                    onValueChange = { taskDescription = it },
                    label = { Text(stringResource(R.string.task_description)) },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 3
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                OutlinedTextField(
                    value = maxDuration,
                    onValueChange = { maxDuration = it },
                    label = { Text(stringResource(R.string.max_duration)) },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    trailingIcon = { Text(stringResource(R.string.minutes)) },
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
                        label = { Text(stringResource(R.string.task_color)) },
                        modifier = Modifier.weight(1f),
                        placeholder = { Text("#FF6B6B") },
                        singleLine = true
                    )
                    
                    Spacer(modifier = Modifier.width(16.dp))
                    
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(
                                color = Color(taskColor.toColorInt()),
                                shape = CircleShape
                            )
                    )
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = "Loại đếm thời gian:",
                    style = MaterialTheme.typography.titleMedium
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    FilterChip(
                        onClick = { isCountdown = true },
                        label = { Text(stringResource(R.string.countdown)) },
                        selected = isCountdown
                    )
                    FilterChip(
                        onClick = { isCountdown = false },
                        label = { Text(stringResource(R.string.count_up)) },
                        selected = !isCountdown
                    )
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
                Text(stringResource(R.string.cancel))
            }
            Button(
                onClick = onSave,
                modifier = Modifier.weight(1f)
            ) {
                Text(stringResource(R.string.save))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenAdminShapeEditor(onSave: () -> Unit, onCancel: () -> Unit) {
    var shapeName by remember { mutableStateOf("") }
    var shapeDescription by remember { mutableStateOf("") }
    var shapeType by remember { mutableStateOf("") }
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
                text = stringResource(R.string.add_shape),
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
                    value = shapeName,
                    onValueChange = { shapeName = it },
                    label = { Text("Tên hình") },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Ví dụ: Tam giác, Chữ nhật, Hình tròn") },
                    singleLine = true
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                OutlinedTextField(
                    value = shapeDescription,
                    onValueChange = { shapeDescription = it },
                    label = { Text("Mô tả") },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Mô tả về hình học này") },
                    minLines = 2
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Box {
                    OutlinedTextField(
                        value = shapeTypeNames.getOrNull(shapeTypes.indexOf(shapeType)) ?: "",
                        onValueChange = { },
                        readOnly = true,
                        label = { Text("Loại hình") },
                        trailingIcon = { 
                            Icon(
                                if (expanded) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                                contentDescription = null
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { expanded = !expanded }
                    )
                    DropdownMenu(
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
                
                if (shapeType.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer
                        )
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "Xem trước:",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Loại: ${shapeTypeNames[shapeTypes.indexOf(shapeType)]}",
                                style = MaterialTheme.typography.bodyMedium
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
                Text(stringResource(R.string.cancel))
            }
            Button(
                onClick = onSave,
                modifier = Modifier.weight(1f)
            ) {
                Text(stringResource(R.string.save))
            }
        }
    }
}

@Composable
fun ScreenSettings(onBack: () -> Unit) {
    var isEnglish by remember { mutableStateOf(false) }
    var notificationsEnabled by remember { mutableStateOf(true) }
    var soundEnabled by remember { mutableStateOf(true) }
    
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
            Text(
                text = stringResource(R.string.settings),
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
                Text(
                    text = stringResource(R.string.language),
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (isEnglish) stringResource(R.string.english) else stringResource(R.string.vietnamese),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Switch(
                        checked = isEnglish,
                        onCheckedChange = { isEnglish = it }
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = "Thông báo",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Bật thông báo",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Switch(
                        checked = notificationsEnabled,
                        onCheckedChange = { notificationsEnabled = it }
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Âm thanh",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Switch(
                        checked = soundEnabled,
                        onCheckedChange = { soundEnabled = it }
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = "Thông tin ứng dụng",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Phiên bản: 1.0.0",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Coloring Shapes App",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Ứng dụng tô màu hình học",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = "Hỗ trợ",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Liên hệ hỗ trợ",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    IconButton(onClick = { }) {
                        Icon(
                            Icons.Default.Email,
                            contentDescription = "Email",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Đánh giá ứng dụng",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    IconButton(onClick = { }) {
                        Icon(
                            Icons.Default.Star,
                            contentDescription = "Rate",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }
}
