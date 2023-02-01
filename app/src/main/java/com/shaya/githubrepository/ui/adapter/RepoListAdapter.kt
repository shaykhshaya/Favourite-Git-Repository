package com.shaya.githubrepository.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shaya.githubrepository.data.RoomItem
import com.shaya.githubrepository.databinding.ListItemBinding

class RepoListAdapter(
    private val onRepoClicked: (RoomItem) -> Unit, val callbackShareItem: (RoomItem) -> Unit
) : ListAdapter<RoomItem, RepoListAdapter.RepoListViewHolder>(DiffCall) {

    class RepoListViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(roomItem: RoomItem) {
            binding.apply {
                textRepo.text = roomItem.name
                textDescription.text = roomItem.description
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoListViewHolder {
        val layout = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoListViewHolder(layout)
    }

    override fun onBindViewHolder(holder: RepoListViewHolder, position: Int) {
        val current = getItem(position)
        holder.binding.textRepo.setOnClickListener {
            onRepoClicked(current)
        }

        holder.bind(current)
        holder.binding.btnShare.setOnClickListener {
            callbackShareItem(current)
        }
    }

    companion object {
        private val DiffCall = object : DiffUtil.ItemCallback<RoomItem>() {
            override fun areItemsTheSame(oldItem: RoomItem, newItem: RoomItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: RoomItem, newItem: RoomItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

}