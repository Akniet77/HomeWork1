package com.example.homework1.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework1.databinding.ItemActivityBinding
import com.example.homework1.model.Task

class ActivityAdapter(private var tasks: MutableList<Task>,
                      private val onCompleteTask: (Int) -> Unit,
                      private val onDeleteTask: (Int) -> Unit
): RecyclerView.Adapter<ActivityAdapter.ViewHolder>(){

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: MutableList<Task>) {
        this.tasks = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemActivityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        holder.onBind(tasks[position])
    }

    override fun getItemCount() = tasks.size

    inner class ViewHolder(private val binding : ItemActivityBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(task: Task) {
            binding.title.text = task.title
            binding.checkboxCompleted.isChecked = task.isCompleted

            binding.checkboxCompleted.setOnCheckedChangeListener { _, isChecked ->
                if (adapterPosition != RecyclerView.NO_POSITION && isChecked) {
                    onCompleteTask(adapterPosition)
                }
            }
        }
    }
}