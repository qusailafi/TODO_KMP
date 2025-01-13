package org.todo.app.presentation.screens.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.todo.app.data.Local
import org.todo.app.utils.RequestState
import org.todo.app.domain.repo.TaskAction
import org.todo.app.domain.model.TodTask

typealias MutableTasks = MutableState<RequestState<List<TodTask>>>
typealias Tasks = MutableState<RequestState<List<TodTask>>>

class HomeViewModel(private val mongoDB: Local) : ScreenModel {
    private var _activeTasks: MutableTasks = mutableStateOf(RequestState.Idle)
    val activeTasks: Tasks = _activeTasks

    private var _completedTasks: MutableTasks = mutableStateOf(RequestState.Idle)
    val completedTasks: Tasks = _completedTasks

    init {
        _activeTasks.value = RequestState.Loading
        _completedTasks.value = RequestState.Loading
        screenModelScope.launch(Dispatchers.Main) {
            delay(500)
            mongoDB.readActiveTasks().collectLatest {
                _activeTasks.value = it
            }
        }
        screenModelScope.launch(Dispatchers.Main) {
            delay(500)
            mongoDB.readCompletedTasks().collectLatest {
                _completedTasks.value = it
            }
        }
    }

    fun setAction(action: TaskAction) {
        when (action) {
            is TaskAction.Delete -> {
                deleteTask(action.task)
            }

            is TaskAction.SetCompleted -> {
                setCompleted(action.task, action.completed)
            }

            is TaskAction.SetFavorite -> {
                setFavorite(action.task, action.isFavorite)
            }

            else -> {}
        }
    }

    private fun setCompleted(task: TodTask, completed: Boolean) {
        screenModelScope.launch(Dispatchers.IO) {
            mongoDB.setCompleted(task, completed)
        }
    }

    private fun setFavorite(task: TodTask, isFavorite: Boolean) {
        screenModelScope.launch(Dispatchers.IO) {
            mongoDB.setFavorite(task, isFavorite)
        }
    }

    private fun deleteTask(task: TodTask) {
        screenModelScope.launch(Dispatchers.IO) {
            mongoDB.deleteTask(task)
        }
    }
}