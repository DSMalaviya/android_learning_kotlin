package com.example.roomdatabasedemo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabasedemo.databinding.ItemsRowBinding

class ItemAdapter(
    private val items: ArrayList<EmployeeEntity>,
    private val updateListner: (id: Int) -> Unit,
    private val deleteListner: (entity: EmployeeEntity) -> Unit
) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemsRowBinding) : RecyclerView.ViewHolder(binding.root) {
        val ivDelete = binding.ivDelete
        val ivEdit = binding.ivEdit
        val tvName = binding.tvName
        val tvEmail = binding.tvEmail
        val llMain = binding.llMain
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemsRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val context = holder.itemView.context
        val item = items[position]
        holder.tvName.text = item.name
        holder.tvEmail.text = item.email
        if (position % 2 == 0) {
            holder.llMain.setBackgroundColor(
                ContextCompat.getColor(holder.itemView.context, R.color.colorLightGray)
            )
        } else {
            holder.llMain.setBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.white
                )
            )
        }
        holder.ivEdit.setOnClickListener {
            updateListner.invoke(items[position].id)
        }
        holder.ivDelete.setOnClickListener {
            deleteListner.invoke(items[position])
        }
    }

    override fun getItemCount(): Int {
        return items.size;
    }
}