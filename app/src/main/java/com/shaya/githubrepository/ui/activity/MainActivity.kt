package com.shaya.githubrepository.ui.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shaya.githubrepository.databinding.ActivityMainBinding
import com.shaya.githubrepository.ui.adapter.RepoListAdapter
import com.shaya.githubrepository.ui.viewmodel.RepoListViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var roomViewModel: RepoListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        roomViewModel = ViewModelProvider(this)[RepoListViewModel::class.java]

        binding.btnAddEmpty.setOnClickListener {
            startActivity(Intent(this, SelectRepoActivity::class.java))
        }
        binding.btnAdd.setOnClickListener {
            startActivity(Intent(this, SelectRepoActivity::class.java))
        }


        val adapter = RepoListAdapter(
            onItemClicked = {
                intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(it.url)
                startActivity(intent)
            },
            callbackShareItem = {

                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "Repository Name: ${it.name} \n Link: ${it.url}")
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)

            })

        binding.recyclerViewList.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.recyclerViewList.adapter = adapter

        roomViewModel.allItems.observe(this) {
            it.let {
                binding.btnContainer.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
                adapter.submitList(it)
            }
        }


    }






}