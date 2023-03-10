package com.shaya.githubrepository.utils

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shaya.githubrepository.R
import com.shaya.githubrepository.network.responses.Item
import com.shaya.githubrepository.ui.adapter.SelectRepoAdapter
import com.shaya.githubrepository.ui.viewmodel.ItemApiStatus

@BindingAdapter("listData")
fun bindRecyclerView(
    recyclerView: RecyclerView,
    data: List<Item>?
) {
    val adapter = recyclerView.adapter as SelectRepoAdapter
    adapter.submitList(data)
}

@BindingAdapter("productApiStatus")
fun bindStatus(
    statusImageView: ImageView,
    status: ItemApiStatus
) {
    when (status) {
        ItemApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.git_icon)
        }
        ItemApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        ItemApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

@BindingAdapter("productApiProgressBar")
fun bindStatus(
    progressBar: ProgressBar,
    status: ItemApiStatus
) {
    when (status) {
        ItemApiStatus.LOADING -> {
            progressBar.visibility = View.VISIBLE
        }
       else -> {
           progressBar.visibility = View.GONE
       }
    }
}