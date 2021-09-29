package com.example.mommymode.data

import android.content.res.Resources
import com.example.mommymode.R

/* Returns initial list of tasks. */
fun taskList(resources: Resources): List<Task> {
    return listOf(
        Task(
            id = 1,
            name = resources.getString(R.string.Responsibility1_name),
            image = R.drawable.eli,
            description = resources.getString(R.string.task1_description)
        ),
        Task(
            id = 2,
            name = resources.getString(R.string.Responsibility2_name),
            image = R.drawable.halani,
            description = resources.getString(R.string.task2_description)
        ),
        Task(
            id = 3,
            name = resources.getString(R.string.Responsibility3_name),
            image = R.drawable.horace,
            description = resources.getString(R.string.task3_description)
        ),
        Task(
            id = 4,
            name = resources.getString(R.string.Responsibility4_name),
            image = R.drawable.car,
            description = resources.getString(R.string.task4_description)
        ),
        Task(
            id = 5,
            name = resources.getString(R.string.Responsibility5_name),
            image = R.drawable.home,
            description = resources.getString(R.string.flower5_description)
        ),
        Task(
            id = 6,
            name = resources.getString(R.string.Responsibility6_name),
            image = R.drawable.mom,
            description = resources.getString(R.string.task6_description)
        ),
        Task(
            id = 7,
            name = resources.getString(R.string.Responsibility7_name),
            image = R.drawable.dad,
            description = resources.getString(R.string.task7_description)
        ),
        Task(
            id = 8,
            name = resources.getString(R.string.Responsibility8_name),
            image = R.drawable.church,
            description = resources.getString(R.string.task8_description)
        ),
        Task(
            id = 9,
            name = resources.getString(R.string.Responsibility9_name),
            image = R.drawable.grocery,
            description = resources.getString(R.string.task9_description)
        ),
        Task(
            id = 10,
            name = resources.getString(R.string.Responsibility10_name),
            image = R.drawable.work,
            description = resources.getString(R.string.task10_description)
        ),
        Task(
            id = 11,
            name = resources.getString(R.string.Responsibility11_name),
            image = R.drawable.school,
            description = resources.getString(R.string.task11_description)
        )
    )
}