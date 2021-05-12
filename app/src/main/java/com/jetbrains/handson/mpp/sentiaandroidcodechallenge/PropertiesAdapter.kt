package com.jetbrains.handson.mpp.sentiaandroidcodechallenge

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jetbrains.handson.mpp.sentiaandroidcodechallenge.databinding.PropertyListItemBinding
import domain.Property


class PropertiesAdapter(val onClickListener: OnClickListener) : ListAdapter<Property , PropertiesAdapter.PropertyViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            PropertiesAdapter.PropertyViewHolder {
        return PropertyViewHolder(PropertyListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: PropertiesAdapter.PropertyViewHolder, position: Int) {
        val property = getItem(position)
        holder.bind(property)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(property)
        }
    }
    companion object DiffCallback : DiffUtil.ItemCallback<Property>() {
        override fun areItemsTheSame(oldItem: Property, newItem: Property): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Property, newItem: Property): Boolean {
            return oldItem.id == newItem.id
        }
    }
    class PropertyViewHolder(private var binding: PropertyListItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(property: Property) {
            binding.property = property
            binding.executePendingBindings()
        }
    }

    class OnClickListener(val clickListener: (marsProperty: Property) -> Unit) {
        fun onClick(marsProperty:Property) = clickListener(marsProperty)
    }
}