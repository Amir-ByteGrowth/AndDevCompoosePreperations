package com.example.paginationjetpackcompose.ui.screens


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.paginationjetpackcompose.models.Item
import com.example.paginationjetpackcompose.repository.ItemRepository


@Composable
fun ItemList1(
    modifier: Modifier = Modifier,
    viewModel: ItemListViewModel = ItemListViewModel(repository = ItemRepository()),
) {
    val items1 = viewModel.items.collectAsLazyPagingItems()
    ListContent(modifier = modifier.fillMaxSize(), items1 = items1)

}


@Composable
fun ListContent(modifier: Modifier = Modifier, items1: LazyPagingItems<Item>) {
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



