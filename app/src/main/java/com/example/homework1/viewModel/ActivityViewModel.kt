package com.example.homework1.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homework1.model.Task

class ActivityViewModel : ViewModel() {
    private val _tasks = MutableLiveData<MutableList<Task>>()
    val tasks: LiveData<MutableList<Task>> = _tasks

    init {
        _tasks.value = mutableListOf()
    }

    fun addTask(title: String) {
        _tasks.value?.add(Task(title))
        _tasks.notifyObserver()
    }

    fun completeTask(position: Int) {
        _tasks.value?.let {
            it[position].isCompleted = true
            _tasks.notifyObserver()
        }
    }

    fun deleteTask(position: Int) {
        _tasks.value?.removeAt(position)
        _tasks.notifyObserver()
    }

    private fun <T> MutableLiveData<T>.notifyObserver() {
        this.value = this.value
    }
}