package com.example.paginationjetpackcompose.ui.screens


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.paginationjetpackcompose.repository.ItemRepository



import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items


import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

import androidx.compose.ui.unit.dp

import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import kotlinx.coroutines.flow.Flow
import androidx.paging.PagingData


@Composable
fun ItemList1(
    modifier: Modifier = Modifier,
    viewModel: ItemListViewModel = ItemListViewModel(repository = ItemRepository()),
) {
    val items1 = viewModel.items.collectAsLazyPagingItems()

    LazyColumn(modifier = modifier) {

        items(items1) { item ->
            item?.let {
                Text(text = it.name, modifier = Modifier.padding(16.dp))
            }
        }

        items1.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { CircularProgressIndicator(modifier = Modifier.fillParentMaxSize()) }
                }

                loadState.append is LoadState.Loading -> {
                    item { CircularProgressIndicator(modifier = Modifier.padding(16.dp)) }
                }

                loadState.refresh is LoadState.Error -> {
                    val e = items1.loadState.refresh as LoadState.Error
                    item {
                        Text(
                            "Error: ${e.error.localizedMessage}",
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }

                loadState.append is LoadState.Error -> {
                    val e = items1.loadState.append as LoadState.Error
                    item {
                        Text(
                            "Error: ${e.error.localizedMessage}",
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}


