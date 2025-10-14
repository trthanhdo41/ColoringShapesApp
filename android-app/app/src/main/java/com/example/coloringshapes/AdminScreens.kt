package com.example.coloringshapes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coloringshapes.data.entities.User
import com.example.coloringshapes.viewmodel.UserViewModel

@Composable
fun ScreenAdminUsersWithDB(
    userViewModel: UserViewModel = viewModel(),
    onBack: () -> Unit
) {
    val users by userViewModel.allUsers.collectAsState(initial = emptyList())
    
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
                text = "Danh sách người dùng",
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.width(48.dp))
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        if (users.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Chưa có người dùng nào")
            }
        } else {
            LazyColumn {
                items(users) { user ->
                    AdminUserCardDB(user = user)
                }
            }
        }
    }
}

@Composable
fun AdminUserCardDB(user: User) {
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
                            color = if (user.isAdmin) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.tertiaryContainer,
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = user.fullName.first().toString(),
                        style = MaterialTheme.typography.titleMedium,
                        color = if (user.isAdmin) MaterialTheme.colorScheme.onError else MaterialTheme.colorScheme.onTertiaryContainer
                    )
                }
                
                Spacer(modifier = Modifier.width(16.dp))
                
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = user.fullName,
                            style = MaterialTheme.typography.titleMedium
                        )
                        if (user.isAdmin) {
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "ADMIN",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
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
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenAdminShapeEditorWithDB(
    shapeViewModel: com.example.coloringshapes.viewmodel.ShapeViewModel = viewModel(),
    onSave: () -> Unit,
    onCancel: () -> Unit
) {
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
                text = "Thêm hình",
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
                    if (shapeName.isNotBlank() && shapeType.isNotBlank()) {
                        shapeViewModel.addShape(
                            name = shapeName,
                            description = shapeDescription,
                            shapeType = shapeType
                        ) {
                            onSave()
                        }
                    }
                },
                modifier = Modifier.weight(1f),
                enabled = shapeName.isNotBlank() && shapeType.isNotBlank()
            ) {
                Text("Lưu")
            }
        }
    }
}

