package com.example.mommymode.taskDetail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.mommymode.R
import com.example.mommymode.addTask.EditTaskActivity
import com.example.mommymode.addTask.TASK_DESCRIPTION
import com.example.mommymode.addTask.TASK_NAME
import com.example.mommymode.taskList.TASK_ID
import android.view.View.OnLongClickListener






class TaskDetailActivity : AppCompatActivity() {

    private val taskDetailViewModel by viewModels<TaskDetailViewModel> {
        TaskDetailViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.task_detail_activity)

        var currentTaskId: Long? = null

        /* Connect variables to UI elements. */
        val taskName: TextView = findViewById(R.id.task_detail_name)
        val taskImage: ImageView = findViewById(R.id.task_detail_image)
        val taskDescription: TextView = findViewById(R.id.task_detail_description)
        val removeTaskButton: Button = findViewById(R.id.remove_button)




        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            currentTaskId = bundle.getLong(TASK_ID)
        }

        /* If currentTaskId is not null, get corresponding task and set name, image and
        description */

        currentTaskId?.let {
            val currentTask = taskDetailViewModel.getTaskID(it)
            taskName.text = currentTask?.name
            if (currentTask?.image == null) {
                taskImage.setImageResource(R.drawable.me)
            } else {
                taskImage.setImageResource(currentTask.image)
            }

            taskDescription.text = currentTask?.description

            removeTaskButton.setOnClickListener {
                if (currentTask != null) {
                    taskDetailViewModel.removeTask(currentTask)
                }
                finish()
            }
        }
        findViewById<TextView>(R.id.task_detail_description).setOnClickListener {
                val intent = Intent(this@TaskDetailActivity, EditTaskActivity::class.java)
                taskName.text.toString()
                taskDescription.text.toString()
                intent.getStringExtra("name")
                intent.getStringExtra("description")
                startActivity(intent)
                setResult(RESULT_OK, intent)
                }






        }
    }



