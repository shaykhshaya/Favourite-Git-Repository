package com.shaya.githubrepository.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shaya.githubrepository.data.RoomItem
import com.shaya.githubrepository.databinding.SelectRepoItemBinding
import com.shaya.githubrepository.network.responses.Item

class SelectRepoAdapter(val callback: (RoomItem) -> Unit): ListAdapter<Item,
        SelectRepoAdapter.ItemSelectViewHolder>(DiffCallback) {

    class ItemSelectViewHolder(private var binding: SelectRepoItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(item: Item){
            binding.textRepo.text = item.name
            binding.textOwner.text = item.owner?.login
            binding.textDescription.text = item.description

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemSelectViewHolder {
        val adapterLayout = SelectRepoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false )
        return ItemSelectViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemSelectViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.itemView.setOnClickListener {
            callback(currentItem)
        }

        holder.bind(currentItem)

    }

    companion object DiffCallback: DiffUtil.ItemCallback<Item>()
    {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }

    }


}