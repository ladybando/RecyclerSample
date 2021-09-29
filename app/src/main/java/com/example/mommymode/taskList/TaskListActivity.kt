
package com.example.mommymode.taskList

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mommymode.addTask.AddTaskActivity
import com.example.mommymode.taskDetail.TaskDetailActivity
import com.example.mommymode.R
import com.example.mommymode.addTask.TASK_DESCRIPTION
import com.example.mommymode.addTask.TASK_NAME
import com.example.mommymode.data.Task

const val TASK_ID = "task id"

class TaskListActivity : AppCompatActivity() {
    private val newTaskActivityRequestCode = 1
    private val tasksListViewModel by viewModels<TODOListViewModel> {
        TODOListViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* Instantiates headerAdapter and taskAdapter. Both adapters are added to concatAdapter.
        which displays the contents sequentially */
        val headerAdapter = HeaderAdapter()
        val taskAdapter = TaskAdapter { task -> adapterOnClick(task) }
        val concatAdapter = ConcatAdapter(headerAdapter, taskAdapter)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = concatAdapter

        tasksListViewModel.TODOLiveData.observe(this, {
            it?.let {
                taskAdapter.submitList(it as MutableList<Task>)
                headerAdapter.updateTaskCount(it.size)
            }
        })

        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener {
            fabOnClick()
        }
    }

    /* Opens TaskDetailActivity when RecyclerView item is clicked. */
    private fun adapterOnClick(task: Task) {
        val intent = Intent(this, TaskDetailActivity()::class.java)
        intent.putExtra(TASK_ID, task.id)
        startActivity(intent)
    }

    /* Adds task to taskList when FAB is clicked. */
    private fun fabOnClick() {
        val intent = Intent(this, AddTaskActivity::class.java)
        startActivityForResult(intent, newTaskActivityRequestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        /* Inserts task into viewModel. */
        if (requestCode == newTaskActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.let { data ->
                val taskName = data.getStringExtra(TASK_NAME)
                val taskDescription = data.getStringExtra(TASK_DESCRIPTION)

                tasksListViewModel.insertTask(taskName, taskDescription)
            }
        }
    }
}