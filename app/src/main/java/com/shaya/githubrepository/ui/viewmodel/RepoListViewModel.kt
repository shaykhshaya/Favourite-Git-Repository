package com.shaya.githubrepository.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.shaya.githubrepository.BaseApplication
import com.shaya.githubrepository.data.RoomItem
import com.shaya.githubrepository.data.RoomItemDao
import kotlinx.coroutines.launch

class RepoListViewModel: ViewModel() {

    private val roomItemDao: RoomItemDao = BaseApplication.database.roomItemDao()

    val allItems: LiveData<List<RoomItem>> = roomItemDao.getItems().asLiveData()

    fun addNewItem(productItem: RoomItem) {
        insertItem(productItem)
    }

    private fun insertItem(productItem: RoomItem) {
        viewModelScope.launch {
            roomItemDao.insert(productItem)
        }
    }

    fun retrieveItem(id: Int): LiveData<RoomItem> {
        return roomItemDao.getItem(id).asLiveData()
    }











}