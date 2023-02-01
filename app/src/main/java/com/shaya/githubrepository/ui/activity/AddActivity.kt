package com.shaya.githubrepository.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.shaya.githubrepository.data.RoomItem
import com.shaya.githubrepository.databinding.ActivityAddBinding
import com.shaya.githubrepository.ui.viewmodel.RepoListViewModel
import com.shaya.githubrepository.ui.viewmodel.SelectRepoViewModel

class AddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding
    private lateinit var roomViewModel: RepoListViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        roomViewModel = ViewModelProvider(this)[RepoListViewModel::class.java]

        val owner:String? = intent.getStringExtra("Owner")
        val repo: String? = intent.getStringExtra("Repository")
        val desc: String? = intent.getStringExtra("Description")
        val url: String? = intent.getStringExtra("url")

        binding.editTextRepository.setText(repo)
        binding.editTextOwnerName.setText(owner)




        binding.btnAdd.setOnClickListener {

            val updatedRepo = binding.editTextRepository.text.toString()
            val updatedOwner = binding.editTextOwnerName.text.toString()
            roomViewModel.addNewItem(
                productItem = RoomItem(
                    name = updatedRepo,
                    owner = updatedOwner,
                    description = desc.toString(),
                    url = url.toString()
                )
            )
            startActivity(Intent(this, MainActivity::class.java))
        }

    }
}