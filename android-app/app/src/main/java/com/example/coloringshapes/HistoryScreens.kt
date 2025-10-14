package com.example.coloringshapes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coloringshapes.data.entities.History
import com.example.coloringshapes.viewmodel.HistoryViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ScreenHistoryWithDB(
    userId: Long,
    historyViewModel: HistoryViewModel = viewModel(),
    onLogout: () -> Unit
) {
    val history by historyViewModel.getUserHistory(userId).collectAsState(initial = emptyList())
    
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Lịch sử",
                style = MaterialTheme.typography.headlineLarge
            )
            IconButton(onClick = onLogout) {
                Icon(Icons.AutoMirrored.Filled.Logout, contentDescription = "Đăng xuất")
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        if (history.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Chưa có lịch sử nào")
            }
        } else {
            LazyColumn {
                items(history) { item ->
                    HistoryCardDB(item = item)
                }
            }
        }
    }
}

@Composable
fun HistoryCardDB(item: History) {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    val date = dateFormat.format(Date(item.completedAt))
    
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
                            color = when {
                                item.score >= 90 -> Color(0xFF22C55E)
                                item.score >= 70 -> Color(0xFFF59E0B)
                                else -> Color(0xFFEF4444)
                            },
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = item.score.toString(),
                        color = Color.White,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                
                Spacer(modifier = Modifier.width(16.dp))
                
                Column {
                    Text(
                        text = "Task ID: ${item.taskId}",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = date,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "Thời gian: ${item.timeTakenSeconds}s",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "${item.score} điểm",
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
fun ScreenAdminReportsWithDB(
    historyViewModel: HistoryViewModel = viewModel(),
    onBack: () -> Unit
) {
    var selectedLimit by remember { mutableIntStateOf(10) }
    val topScores by historyViewModel.getTopScores(selectedLimit).collectAsState(initial = emptyList())
    
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
                text = "Báo cáo điểm số",
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
                        onClick = { selectedLimit = 3 },
                        label = { Text("Top 3") },
                        selected = selectedLimit == 3
                    )
                    FilterChip(
                        onClick = { selectedLimit = 10 },
                        label = { Text("Top 10") },
                        selected = selectedLimit == 10
                    )
                    FilterChip(
                        onClick = { selectedLimit = 100 },
                        label = { Text("Top 100") },
                        selected = selectedLimit == 100
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        if (topScores.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Chưa có dữ liệu")
            }
        } else {
            LazyColumn {
                items(topScores.take(selectedLimit)) { score ->
                    AdminScoreCardDB(score = score, rank = topScores.indexOf(score) + 1)
                }
            }
        }
    }
}

@Composable
fun AdminScoreCardDB(score: History, rank: Int) {
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
                            color = when (rank) {
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
                        text = rank.toString(),
                        style = MaterialTheme.typography.titleMedium,
                        color = if (rank <= 3) Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                
                Spacer(modifier = Modifier.width(16.dp))
                
                Text(
                    text = "User ID: ${score.userId}",
                    style = MaterialTheme.typography.titleMedium
                )
            }
            
            Text(
                text = "${score.score} điểm",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

