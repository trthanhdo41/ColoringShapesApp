package com.example.coloringshapes

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coloringshapes.data.entities.Task
import com.example.coloringshapes.viewmodel.HistoryViewModel
import kotlinx.coroutines.delay
import java.util.Locale

@Composable
fun ScreenColoringWithDB(
    task: Task,
    userId: Long,
    historyViewModel: HistoryViewModel = viewModel(),
    onComplete: () -> Unit,
    onBack: () -> Unit
) {
    var selectedColor by remember { mutableStateOf(Color(0xFF4F8CFF)) }
    var strokes by remember { mutableStateOf(listOf<List<Offset>>()) }
    var currentStroke by remember { mutableStateOf(listOf<Offset>()) }
    var timeRemaining by remember { mutableIntStateOf(task.durationMinutes * 60) }
    var timeTaken by remember { mutableIntStateOf(0) }
    var isActive by remember { mutableStateOf(true) }
    
    LaunchedEffect(isActive) {
        while (isActive && timeRemaining > 0) {
            delay(1000)
            timeRemaining--
            timeTaken++
        }
        if (timeRemaining <= 0) {
            isActive = false
        }
    }
    
    fun saveAndComplete() {
        val score = calculateScore(strokes)
        val status = if (timeRemaining > 0) "completed" else "time_up"
        
        historyViewModel.saveHistory(
            userId = userId,
            taskId = task.id,
            score = score,
            timeTakenSeconds = timeTaken,
            status = status
        ) {
            onComplete()
        }
    }
    
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
                text = "Tô màu",
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
                    text = "Thời gian: ${formatTime(timeRemaining)}",
                    style = MaterialTheme.typography.titleMedium,
                    color = if (timeRemaining < 60) Color.Red else MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Điểm: ${calculateScore(strokes)}",
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
                
                when (task.shapeType) {
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
                onClick = { saveAndComplete() },
                modifier = Modifier.weight(2f)
            ) {
                Text("Hoàn thành")
            }
        }
    }
}


private fun formatTime(seconds: Int): String {
    val minutes = seconds / 60
    val remainingSeconds = seconds % 60
    return String.format(Locale.getDefault(), "%02d:%02d", minutes, remainingSeconds)
}

private fun calculateScore(strokes: List<List<Offset>>): Int {
    return (strokes.sumOf { it.size } * 2).coerceIn(0, 100)
}

