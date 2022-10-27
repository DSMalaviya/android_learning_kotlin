package com.example.recyclerviewdemo

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewdemo.databinding.RecyclerviewItemBinding
import es.dmoral.toasty.Toasty

class MainAdapter(val taskList:List<Task>) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    inner class MainViewHolder(val itemBinding: RecyclerviewItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bindItem(task: Task) {
            itemBinding.titleTV.text = task.title
            itemBinding.timeTV.text = task.timestamp
            itemBinding.root.setOnClickListener{
                Toasty.success(itemBinding.root.context.applicationContext,"${task.title}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val task=taskList[position]
        holder.bindItem(task)
    }

    override fun getItemCount(): Int {
        return  taskList.size
    }
}