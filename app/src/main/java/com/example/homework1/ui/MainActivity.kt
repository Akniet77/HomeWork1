package com.example.homework1.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework1.adapter.ActivityAdapter
import com.example.homework1.databinding.ActivityMainBinding
import com.example.homework1.viewModel.ActivityViewModel


class MainActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProvider(this)[ActivityViewModel::class.java] }
    private lateinit var binding: ActivityMainBinding
    private lateinit var taskAdapter : ActivityAdapter

    companion object{
        private const val REQUEST_CODE_ADD_TASK = 1
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        taskAdapter = ActivityAdapter(viewModel.tasks.value ?: mutableListOf(), this::onCompleteTask, this::onDeleteTask)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = taskAdapter
        }


        viewModel.tasks.observe(this) { tasks ->
            if (tasks != null) {
            taskAdapter.setList(tasks)
            }
        }
        binding.recyclerView.post {
            taskAdapter.notifyDataSetChanged()
        }

        binding.addNoteBtn.setOnClickListener{
            val intent = Intent(this, SecondActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_ADD_TASK)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_ADD_TASK &&  resultCode == Activity.RESULT_OK){
            val taskTitle = data?.getStringExtra("title")
            if (!taskTitle.isNullOrEmpty()){
                viewModel.addTask(taskTitle)
            }
        }
    }



    private fun onCompleteTask(position: Int) {
        viewModel.completeTask(position)
    }

    private fun onDeleteTask(position: Int) {
        viewModel.deleteTask(position)
    }
}