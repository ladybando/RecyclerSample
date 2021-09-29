

package com.example.mommymode.taskDetail

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mommymode.data.DataSource
import com.example.mommymode.data.Task

class TaskDetailViewModel(private val datasource: DataSource) : ViewModel() {

    /* Queries datasource to returns a task that corresponds to an id. */
    fun getTaskID(id: Long) : Task? {
        return datasource.getTaskForId(id)
    }

    /* Queries datasource to remove a task. */

    fun removeTask(task: Any) {
        datasource.removeTask(task as Task)
    }

    }


class TaskDetailViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TaskDetailViewModel(
                datasource = DataSource.getDataSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}