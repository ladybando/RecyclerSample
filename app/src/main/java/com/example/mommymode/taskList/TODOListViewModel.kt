
package com.example.mommymode.taskList

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mommymode.data.DataSource
import com.example.mommymode.data.Task
import kotlin.random.Random

class TODOListViewModel(val dataSource: DataSource) : ViewModel() {

    val TODOLiveData = dataSource.getTODOList()

    /* If the name and description are present, create new task and add it to the datasource */
    fun insertTask(taskTitle: String?, taskDescription: String?) {
        if (taskTitle == null || taskDescription == null) {
            return
        }

        val image = dataSource.getRandomTaskImageAsset()
        val newTask = Task(
            Random.nextLong(),
            taskTitle,
            image,
            taskDescription
        )

        dataSource.addTask(newTask)
    }
}

class TODOListViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TODOListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TODOListViewModel(
                dataSource = DataSource.getDataSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}