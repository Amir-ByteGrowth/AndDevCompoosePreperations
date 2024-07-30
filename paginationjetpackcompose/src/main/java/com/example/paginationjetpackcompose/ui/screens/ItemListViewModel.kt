package com.example.paginationjetpackcompose.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.paginationjetpackcompose.models.Item
import com.example.paginationjetpackcompose.repository.ItemRepository
import kotlinx.coroutines.flow.Flow


class ItemListViewModel(private val repository: ItemRepository) : ViewModel() {

    val items: Flow<PagingData<Item>> = repository.getItemsStream()
        .cachedIn(viewModelScope)
}