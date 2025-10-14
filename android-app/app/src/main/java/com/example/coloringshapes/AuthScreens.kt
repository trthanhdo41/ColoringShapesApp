package com.example.coloringshapes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coloringshapes.viewmodel.AuthViewModel
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenLoginWithDB(
    authViewModel: AuthViewModel = viewModel(),
    onLoggedIn: () -> Unit,
    onRegister: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val loginError by authViewModel.loginError.collectAsState()
    
    LaunchedEffect(Unit) {
        authViewModel.clearErrors()
    }
    
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
                    text = "Đăng nhập",
                    style = MaterialTheme.typography.headlineLarge
                )
                
                Spacer(modifier = Modifier.height(32.dp))
                
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                    singleLine = true
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Mật khẩu") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation(),
                    leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                    singleLine = true
                )
                
                if (loginError != null) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = loginError ?: "",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Button(
                    onClick = {
                        authViewModel.login(email, password) { user ->
                            onLoggedIn()
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = email.isNotBlank() && password.isNotBlank()
                ) {
                    Text("Đăng nhập")
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                TextButton(onClick = onRegister) {
                    Text("Đăng ký tài khoản mới")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenRegisterWithDB(
    authViewModel: AuthViewModel = viewModel(),
    onRegistered: () -> Unit,
    onLogin: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var fullName by remember { mutableStateOf("") }
    var birthYear by remember { mutableStateOf(2000) }
    var showDatePicker by remember { mutableStateOf(false) }
    var gender by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val registerError by authViewModel.registerError.collectAsState()
    
    LaunchedEffect(Unit) {
        authViewModel.clearErrors()
    }
    
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
                    text = "Đăng ký",
                    style = MaterialTheme.typography.headlineLarge
                )
                
                Spacer(modifier = Modifier.height(32.dp))
                
                OutlinedTextField(
                    value = fullName,
                    onValueChange = { fullName = it },
                    label = { Text("Họ tên") },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                    singleLine = true
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                    singleLine = true
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Mật khẩu") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation(),
                    leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                    singleLine = true
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Date Picker for Birth Year
                OutlinedTextField(
                    value = birthYear.toString(),
                    onValueChange = { },
                    label = { Text("Năm sinh") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { showDatePicker = true },
                    leadingIcon = { Icon(Icons.Default.CalendarToday, contentDescription = null) },
                    readOnly = true,
                    singleLine = true
                )
                
                // Date Picker Dialog
                if (showDatePicker) {
                    val datePickerState = rememberDatePickerState(
                        initialSelectedDateMillis = Calendar.getInstance().apply {
                            set(Calendar.YEAR, birthYear)
                            set(Calendar.MONTH, 0)
                            set(Calendar.DAY_OF_MONTH, 1)
                        }.timeInMillis
                    )
                    
                    AlertDialog(
                        onDismissRequest = { showDatePicker = false },
                        title = { Text("Chọn năm sinh") },
                        text = {
                            DatePicker(state = datePickerState)
                        },
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    datePickerState.selectedDateMillis?.let { millis ->
                                        val calendar = Calendar.getInstance()
                                        calendar.timeInMillis = millis
                                        birthYear = calendar.get(Calendar.YEAR)
                                    }
                                    showDatePicker = false
                                }
                            ) {
                                Text("OK")
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = { showDatePicker = false }) {
                                Text("Hủy")
                            }
                        }
                    )
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = it }
                ) {
                    OutlinedTextField(
                        value = gender,
                        onValueChange = { },
                        readOnly = true,
                        label = { Text("Giới tính") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor()
                    )
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Nam") },
                            onClick = { gender = "Nam"; expanded = false }
                        )
                        DropdownMenuItem(
                            text = { Text("Nữ") },
                            onClick = { gender = "Nữ"; expanded = false }
                        )
                    }
                }
                
                if (registerError != null) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = registerError ?: "",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Button(
                    onClick = {
                        authViewModel.register(fullName, email, password, birthYear, gender) {
                            onRegistered()
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = fullName.isNotBlank() && email.isNotBlank() && password.isNotBlank() && birthYear > 1900 && gender.isNotBlank()
                ) {
                    Text("Đăng ký")
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                TextButton(onClick = onLogin) {
                    Text("Đã có tài khoản? Đăng nhập")
                }
            }
        }
    }
}

