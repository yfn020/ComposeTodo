package app.softwork.composetodo.todos

import androidx.compose.runtime.*
import app.softwork.composetodo.*
import app.softwork.composetodo.dto.Todo
import kotlinx.uuid.*

@Composable
fun Todo(api: API.LoggedIn, todoID: UUID) {
    var todo: Todo? by remember { mutableStateOf(null) }
    LaunchedEffect(todoID) {
        todo = api.getTodo(todoID)
    }
    todo?.let {
        Text(it.title)
    }
}