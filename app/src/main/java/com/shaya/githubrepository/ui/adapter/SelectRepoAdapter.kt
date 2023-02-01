package com.shaya.githubrepository.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shaya.githubrepository.databinding.SelectRepoItemBinding
import com.shaya.githubrepository.network.responses.Item

class SelectRepoAdapter(
    val callbackShareRepo: (Item) -> Unit,
    val callbackAddItem: (Item) -> Unit
) : ListAdapter<Item,
        SelectRepoAdapter.ItemSelectViewHolder>(DiffCallback) {

    class ItemSelectViewHolder(var binding: SelectRepoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.textRepo.text = item.name
            binding.textOwner.text = item.owner?.login
            binding.textDescription.text = item.description

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemSelectViewHolder {
        val adapterLayout =
            SelectRepoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemSelectViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemSelectViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.binding.textRepo.setOnClickListener {
            callbackShareRepo(currentItem)
        }
        holder.binding.textOwner.setOnClickListener {
            holder.binding.textRepo.performClick()
        }

        holder.bind(currentItem)
        holder.binding.btnAdd.setOnClickListener {
            callbackAddItem(currentItem)
        }

    }

    companion object DiffCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }
    }

}