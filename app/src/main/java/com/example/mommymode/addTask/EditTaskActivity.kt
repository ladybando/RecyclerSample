package com.example.mommymode.addTask

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import com.example.mommymode.R
import com.example.mommymode.data.Task
import com.example.mommymode.taskDetail.TaskDetailActivity
import com.example.mommymode.taskList.TaskAdapter

class EditTaskActivity : AppCompatActivity() {
    lateinit var category: EditText
    lateinit var editTask: EditText
    lateinit var button: Button
    private var currentTask: Task? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_task_activity)

        val category = findViewById<EditText>(R.id.edit_task_name)
        val editTask = findViewById<EditText>(R.id.edit_task_description)
        val ebutton = findViewById<Button>(R.id.edit_button)

        ebutton.setOnClickListener {
            editTask()
            val i = Intent(this@EditTaskActivity, Activity::class.java)
            i.putExtra("name", category.text.toString())
            i.putExtra("description", editTask.text.toString())
            startActivity(i)

        }
        /* The onClick action for the done button. Closes the activity and returns the edited task name
           and description as part of the intent. If the name or description are missing, the result is set
           to cancelled. */

    }

    private fun editTask() {
        val rIntent = Intent()

        if (category.text.isNullOrEmpty() || editTask.text.isNullOrEmpty()) {
            setResult(Activity.RESULT_CANCELED, rIntent)
        } else {
            val name = category.text.toString()
            val description = editTask.text.toString()
            rIntent.putExtra(TASK_NAME, name)
            rIntent.putExtra(TASK_DESCRIPTION, description)
            setResult(Activity.RESULT_OK, rIntent)

        }
    }
}

