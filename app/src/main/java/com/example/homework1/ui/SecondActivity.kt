package com.example.homework1.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.homework1.adapter.ActivityAdapter
import com.example.homework1.databinding.ActivityMainBinding
import com.example.homework1.databinding.ActivitySecondBinding
import com.example.homework1.model.Task
import com.example.homework1.viewModel.ActivityViewModel

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private val viewModel by lazy { ViewModelProvider(this)[ActivityViewModel::class.java] }


    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnAdd.setOnClickListener {
            val title = binding.editText.text.toString().trim()
            if (title.isNotEmpty()) {
                viewModel.addTask(title)
                val  result = Intent()
                result.putExtra("title", title)
                setResult(Activity.RESULT_OK, result)
                finish()
            }
        }
    }
}
