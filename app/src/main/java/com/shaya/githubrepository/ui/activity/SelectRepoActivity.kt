package com.shaya.githubrepository.ui.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.shaya.githubrepository.data.RoomItem
import com.shaya.githubrepository.databinding.ActivitySelectRepoBinding
import com.shaya.githubrepository.network.responses.Item
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


binding.selectRepoRecyclerView.adapter = SelectRepoAdapter(
    callback = {
        val roomItem = RoomItem(
            name = it.name.toString(), owner = it.owner?.login.toString(), description = it.description.toString(), url = it.html_url.toString()
        )

        intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(roomItem.url)
        startActivity(intent)



        //roomViewModel.addNewItem(roomItem)
        //finish()
    },
    callbackAddItem = {

        val intent = Intent(this, AddActivity::class.java)

        intent.putExtra("Owner", it.owner?.login.toString())
        intent.putExtra("Repository", it.name.toString())
        intent.putExtra("Description", it.description.toString())
        intent.putExtra("url", it.html_url.toString())
        startActivity(intent)

    }

)
        /*binding.selectRepoRecyclerView.adapter = SelectRepoAdapter{
            val roomItem = RoomItem(
                 name = it.name.toString(), owner = it.owner?.login.toString(), description = it.description.toString(), url = it.html_url.toString()
            )

            intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(roomItem.url)
            startActivity(intent)



            //roomViewModel.addNewItem(roomItem)
            //finish()
        }*/





    }
}