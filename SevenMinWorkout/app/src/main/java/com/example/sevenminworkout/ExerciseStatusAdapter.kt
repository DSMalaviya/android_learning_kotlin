package com.example.sevenminworkout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.sevenminworkout.databinding.ItemExerciseStatusBinding

class ExerciseStatusAdapter(private val items:ArrayList<ExerciseModel>):
    RecyclerView.Adapter<ExerciseStatusAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemExerciseStatusBinding):RecyclerView.ViewHolder(binding.root){
        val tvItem=binding.tvItem
        val exerciseStatusConstraint=binding.exerciseStatusConstraint
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemExerciseStatusBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model:ExerciseModel=items[position]
        holder.tvItem.text=model.getId().toString()
        when{
            model.getIsCompleted()->{
                holder.exerciseStatusConstraint.background=ContextCompat.getDrawable(holder.itemView.context,R.drawable.item_circular_color_accent_background)
                holder.tvItem.setTextColor(ContextCompat.getColor(holder.itemView.context,R.color.white))
            }
            model.getIsSelected()->{
                holder.exerciseStatusConstraint.background=ContextCompat.getDrawable(holder.itemView.context,R.drawable.item_circular_color_accent_border)
                holder.tvItem.setTextColor(ContextCompat.getColor(holder.itemView.context,R.color.black))
            }
            else->{
                holder.exerciseStatusConstraint.background=ContextCompat.getDrawable(holder.itemView.context,R.drawable.item_circular_color_grey_background)
                holder.tvItem.setTextColor(ContextCompat.getColor(holder.itemView.context,R.color.black))
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}