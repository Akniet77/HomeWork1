package com.example.homework1.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.homework1.adapter.ActivityAdapter
import com.example.homework1.databinding.ActivityMainBinding
import com.example.homework1.viewModel.ActivityViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProvider(this)[ActivityViewModel::class.java] }
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ActivityAdapter(viewModel.tasks.value ?: mutableListOf(), this::onCompleteTask, this::onDeleteTask)
        binding.recyclerView.adapter = adapter


        viewModel.tasks.observe(this, Observer {
            adapter.notifyDataSetChanged()
        })

        binding.addNoteBtn.setOnClickListener{
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

    }

    private fun onCompleteTask(position: Int) {
        viewModel.completeTask(position)
    }

    private fun onDeleteTask(position: Int) {
        viewModel.deleteTask(position)
    }
}
