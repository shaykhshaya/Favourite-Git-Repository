package com.shaya.githubrepository.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shaya.githubrepository.data.RoomItem
import com.shaya.githubrepository.databinding.SelectRepoItemBinding
import com.shaya.githubrepository.network.responses.Item
import com.shaya.githubrepository.ui.activity.AddActivity

class SelectRepoAdapter(val callback: (Item) -> Unit, val callbackAddItem: (Item) -> Unit): ListAdapter<Item,
        SelectRepoAdapter.ItemSelectViewHolder>(DiffCallback) {

    class ItemSelectViewHolder(var binding: SelectRepoItemBinding): RecyclerView.ViewHolder(binding.root){

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
        holder.binding.btnAdd.setOnClickListener {
            callbackAddItem(currentItem)
        }

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