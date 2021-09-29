

package com.example.mommymode.data

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mommymode.R

/* Handles operations on TODOLiveData and holds details about it. */
class DataSource(resources: Resources) {
    private val initialTODOList = taskList(resources)
    private val toDoList = MutableLiveData(initialTODOList)

    /* Adds task to liveData and posts value. */
    fun addTask(task: Task) {
        val currentList = toDoList.value
        if (currentList == null) {
            toDoList.postValue(listOf(task))
        } else {
            val updatedList = currentList.toMutableList()
            updatedList.add(0, task)
            toDoList.postValue(updatedList)
        }
    }

    /* Removes tasks from liveData and posts value. */
    fun removeTask(task: Task) {
        val currentList = toDoList.value
        if (currentList != null) {
            val updatedList = currentList.toMutableList()
            updatedList.remove(task)
            toDoList.postValue(updatedList)
        }
    }

    /* Returns tasks given an ID. */
    fun getTaskForId(id: Long): Task? {
        toDoList.value?.let { tasks ->
            return tasks.firstOrNull { it?.id == id }
        }
        return null
    }

    fun getTODOList(): LiveData<List<Task>> {
        return toDoList
    }

    /* Returns image of me for tasks that are added. */
    fun getRandomTaskImageAsset(): Int {
        //val randomNumber = (initialTODOList.indices).random()
        return R.drawable.me
    }



        companion object {
            private var INSTANCE: DataSource? = null

            fun getDataSource(resources: Resources): DataSource {
                return synchronized(DataSource::class) {
                    val newInstance = INSTANCE ?: DataSource(resources)
                    INSTANCE = newInstance
                    newInstance
                }
            }
        }
    }
