package com.example.coloringshapes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coloringshapes.data.repository.ColoringRepository
import com.example.coloringshapes.viewmodel.TaskViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppRoot()
        }
    }
}

object Routes {
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val TASKS = "tasks"
    const val HISTORY = "history"
    const val ADMIN = "admin"
    const val TASK_DETAIL = "task_detail"
    const val COLORING = "coloring"
    const val SETTINGS = "settings"
    const val ADMIN_TASKS = "admin_tasks"
    const val ADMIN_SHAPES = "admin_shapes"
    const val ADMIN_USERS = "admin_users"
    const val ADMIN_REPORTS = "admin_reports"
    const val ADMIN_ARTWORKS = "admin_artworks"
    const val ADMIN_TASK_EDITOR = "admin_task_editor"
    const val ADMIN_SHAPE_EDITOR = "admin_shape_editor"
}

@Composable
fun AppRoot() {
    val context = LocalContext.current
    val nav = rememberNavController()
    
    // Initialize database with sample data
    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            val repository = ColoringRepository(context)
            repository.initializeSampleData()
        }
    }
    
    Surface(color = MaterialTheme.colorScheme.background) {
        Scaffold(
            bottomBar = { BottomBar(nav) }
        ) { inner ->
            AppNavHost(nav, Modifier.padding(inner))
        }
    }
}

@Composable
fun AppNavHost(nav: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = nav, startDestination = Routes.LOGIN, modifier = modifier) {
        composable(Routes.LOGIN) { 
            ScreenLoginWithDB(
                onLoggedIn = { nav.navigate(Routes.TASKS) },
                onRegister = { nav.navigate(Routes.REGISTER) }
            ) 
        }
        composable(Routes.REGISTER) { 
            ScreenRegisterWithDB(
                onRegistered = { nav.navigate(Routes.LOGIN) },
                onLogin = { nav.navigate(Routes.LOGIN) }
            ) 
        }
        composable(Routes.TASKS) {
            val taskViewModel: TaskViewModel = viewModel()
            ScreenTasksWithDB(
                taskViewModel = taskViewModel,
                onOpenDetail = { task ->
                    taskViewModel.selectTask(task)
                    nav.navigate(Routes.TASK_DETAIL)
                },
                onLogout = { nav.navigate(Routes.LOGIN) }
            ) 
        }
        composable(Routes.HISTORY) { 
            // TODO: Get actual user ID from AuthViewModel
            ScreenHistoryWithDB(
                userId = 1L,
                onLogout = { nav.navigate(Routes.LOGIN) }
            ) 
        }
        composable(Routes.ADMIN) { 
            ScreenAdmin(
                nav = nav,
                onLogout = { nav.navigate(Routes.LOGIN) }
            ) 
        }
        composable(Routes.TASK_DETAIL) { 
            ScreenTaskDetail(
                onStart = { nav.navigate(Routes.COLORING) },
                onBack = { nav.popBackStack() }
            ) 
        }
        composable(Routes.COLORING) {
            val taskViewModel: TaskViewModel = viewModel()
            val selectedTask by taskViewModel.selectedTask.collectAsState()
            if (selectedTask != null) {
                ScreenColoringWithDB(
                    task = selectedTask!!,
                    userId = 1L, // TODO: Get actual user ID
                    onComplete = { nav.navigate(Routes.TASKS) },
                    onBack = { nav.popBackStack() }
                )
            }
        }
        composable(Routes.SETTINGS) { 
            ScreenSettings(
                onBack = { nav.popBackStack() }
            ) 
        }
        composable(Routes.ADMIN_TASKS) { 
            ScreenAdminTasks(
                onAdd = { nav.navigate(Routes.ADMIN_TASK_EDITOR) },
                onBack = { nav.popBackStack() }
            ) 
        }
        composable(Routes.ADMIN_SHAPES) { 
            ScreenAdminShapes(
                onAdd = { nav.navigate(Routes.ADMIN_SHAPE_EDITOR) },
                onBack = { nav.popBackStack() }
            ) 
        }
        composable(Routes.ADMIN_USERS) { 
            ScreenAdminUsersWithDB(
                onBack = { nav.popBackStack() }
            ) 
        }
        composable(Routes.ADMIN_REPORTS) { 
            ScreenAdminReportsWithDB(
                onBack = { nav.popBackStack() }
            ) 
        }
        composable(Routes.ADMIN_ARTWORKS) { 
            ScreenAdminArtworks(
                onBack = { nav.popBackStack() }
            ) 
        }
        composable(Routes.ADMIN_TASK_EDITOR) { 
            ScreenAdminTaskEditorWithDB(
                onSave = { nav.popBackStack() },
                onCancel = { nav.popBackStack() }
            ) 
        }
        composable(Routes.ADMIN_SHAPE_EDITOR) { 
            ScreenAdminShapeEditorWithDB(
                onSave = { nav.popBackStack() },
                onCancel = { nav.popBackStack() }
            ) 
        }
    }
}

@Composable
fun BottomBar(nav: NavHostController) {
    val current = nav.currentBackStackEntryAsState().value?.destination?.route
    val showBottomBar = current in listOf(Routes.TASKS, Routes.HISTORY, Routes.ADMIN, Routes.SETTINGS)
    
    if (showBottomBar) {
        NavigationBar {
            NavItem(
                label = stringResource(R.string.tasks), 
                selected = current == Routes.TASKS
            ) { nav.navigate(Routes.TASKS) }
            NavItem(
                label = stringResource(R.string.history), 
                selected = current == Routes.HISTORY
            ) { nav.navigate(Routes.HISTORY) }
            NavItem(
                label = stringResource(R.string.admin), 
                selected = current == Routes.ADMIN
            ) { nav.navigate(Routes.ADMIN) }
            NavItem(
                label = stringResource(R.string.settings), 
                selected = current == Routes.SETTINGS
            ) { nav.navigate(Routes.SETTINGS) }
        }
    }
}

@Composable
fun RowScope.NavItem(label: String, selected: Boolean, onClick: () -> Unit) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = {},
        label = { Text(label) }
    )
}