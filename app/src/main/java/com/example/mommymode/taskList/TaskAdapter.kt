
package com.example.mommymode.taskList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mommymode.R
import com.example.mommymode.data.Task


class TaskAdapter(private val onClick: (Task) -> Unit) :
    ListAdapter<Task, TaskAdapter.TaskViewHolder>(TaskDiffCallback) {

    /* ViewHolder for task, takes in the inflated view and the onClick behavior. */
    class TaskViewHolder(itemView: View, val onClick: (Task) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val taskTextView: TextView = itemView.findViewById(R.id.task_text)
        private val taskImageView: ImageView = itemView.findViewById(R.id.task_image)
        private var currentTask: Task? = null


        init {
            itemView.setOnClickListener {
                currentTask?.let {
                    onClick(it)
                }
            }
        }

        /* Bind person name and image. */
        fun bind(task: Task) {
            currentTask = task

            taskTextView.text = task.name
            if (task.image != null) {
                taskImageView.setImageResource(task.image)
            } else {
                taskImageView.setImageResource(R.drawable.me)
            }
        }
    }

    /* Creates and inflates view and return TaskViewHolder. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(view, onClick)

    }

    /* Gets current tasks and uses it to bind view. */
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = getItem(position)
        holder.bind(task)

    }
}

object TaskDiffCallback : DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.id == newItem.id
    }
}
