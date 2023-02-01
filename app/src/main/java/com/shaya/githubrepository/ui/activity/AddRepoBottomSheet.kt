package com.shaya.githubrepository.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.shaya.githubrepository.data.RoomItem
import com.shaya.githubrepository.databinding.SheetAddRepoBinding
import com.shaya.githubrepository.network.responses.Item
import com.shaya.githubrepository.ui.viewmodel.RepoListViewModel
import com.shaya.githubrepository.utils.openLink

class AddRepoBottomSheet(private val item: Item) : BottomSheetDialogFragment() {

    private lateinit var binding: SheetAddRepoBinding
    private lateinit var roomViewModel: RepoListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SheetAddRepoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        roomViewModel = ViewModelProvider(this)[RepoListViewModel::class.java]
        initViews()
    }


    private fun initViews() {
        binding.apply {
            textRepo.text = item.name
            textDescription.text = item.description
            editTextRepository.setText(item.name)
            editTextOwnerName.setText(item.owner?.login)
            textRepo.setOnClickListener {
                context?.openLink(item.html_url.toString())
            }
        }

        binding.btnAdd.setOnClickListener {
            val updatedRepo = binding.editTextRepository.text.toString()
            val updatedOwner = binding.editTextOwnerName.text.toString()
            roomViewModel.addNewItem(
                productItem = RoomItem(
                    name = updatedRepo,
                    owner = updatedOwner,
                    description = item.description.toString(),
                    url = item.html_url.toString()
                )
            )
            requireActivity().finish()
        }
    }

}