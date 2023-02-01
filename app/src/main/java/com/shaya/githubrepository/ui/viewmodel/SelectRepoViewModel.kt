package com.shaya.githubrepository.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shaya.githubrepository.network.ItemApi
import com.shaya.githubrepository.network.responses.Item
import kotlinx.coroutines.launch

enum class ItemApiStatus { LOADING, ERROR, DONE }

class SelectRepoViewModel : ViewModel() {

    private val _status = MutableLiveData<ItemApiStatus>()
    val status: LiveData<ItemApiStatus> = _status

    private val _items = MutableLiveData<List<Item>?>()
    val items: MutableLiveData<List<Item>?> = _items

    init {
        getItemList()
    }

    private fun getItemList() {
        viewModelScope.launch {
            _status.value = ItemApiStatus.LOADING
            try {
                _items.value = ItemApi.retrofitService.getFavouriteRepositories().items
                _status.value = ItemApiStatus.DONE
            } catch (e: Exception) {
                _status.value = ItemApiStatus.ERROR
                _items.value = listOf()
            }
        }
    }


}