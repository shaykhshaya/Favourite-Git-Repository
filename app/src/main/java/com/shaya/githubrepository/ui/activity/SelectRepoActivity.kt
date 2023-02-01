package com.shaya.githubrepository.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.shaya.githubrepository.databinding.ActivitySelectRepoBinding
import com.shaya.githubrepository.ui.adapter.SelectRepoAdapter
import com.shaya.githubrepository.ui.viewmodel.RepoListViewModel
import com.shaya.githubrepository.ui.viewmodel.SelectRepoViewModel

class SelectRepoActivity : AppCompatActivity() {

    lateinit var binding: ActivitySelectRepoBinding
    private lateinit var viewModel: SelectRepoViewModel
    private lateinit var roomViewModel: RepoListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectRepoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[SelectRepoViewModel::class.java]
        roomViewModel = ViewModelProvider(this)[RepoListViewModel::class.java]

        binding.apply {
            viewModel = this@SelectRepoActivity.viewModel
            lifecycleOwner = this@SelectRepoActivity
        }

        binding.selectRepoRecyclerView.adapter = SelectRepoAdapter{
            roomViewModel.addNewItem(it)
            finish()
        }



    }
}