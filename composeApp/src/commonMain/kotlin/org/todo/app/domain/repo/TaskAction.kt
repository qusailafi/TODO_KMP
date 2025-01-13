package org.todo.app.domain.repo

import org.todo.app.domain.model.TodTask

sealed class TaskAction {
    data class Add(val task: TodTask) : TaskAction()
    data class Update(val task: TodTask) : TaskAction()
    data class Delete(val task: TodTask) : TaskAction()
    data class SetCompleted(val task: TodTask, val completed: Boolean) : TaskAction()
    data class SetFavorite(val task: TodTask, val isFavorite: Boolean) : TaskAction()
}