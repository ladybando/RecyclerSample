
package com.example.mommymode.addTask

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.mommymode.R
import com.google.android.material.textfield.TextInputEditText

const val TASK_NAME = "name"
const val TASK_DESCRIPTION = "description"



class AddTaskActivity : AppCompatActivity() {
    private lateinit var addTaskName: TextInputEditText
    private lateinit var addTaskDescription: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_task_layout)

        findViewById<Button>(R.id.done_button).setOnClickListener {
            addTask()
        }
        addTaskName = findViewById(R.id.add_task_name)
        addTaskDescription = findViewById(R.id.add_task_description)
    }

    /* The onClick action for the done button. Closes the activity and returns the new task name
    and description as part of the intent. If the name or description are missing, the result is set
    to cancelled. */

    private fun addTask() {
        val resultIntent = Intent()

        if (addTaskName.text.isNullOrEmpty() || addTaskDescription.text.isNullOrEmpty()) {
            setResult(Activity.RESULT_CANCELED, resultIntent)
        } else {
            val name = addTaskName.text.toString()
            val description = addTaskDescription.text.toString()
            resultIntent.putExtra(TASK_NAME, name)
            resultIntent.putExtra(TASK_DESCRIPTION, description)
            setResult(Activity.RESULT_OK, resultIntent)
        }

        finish()
    }
}