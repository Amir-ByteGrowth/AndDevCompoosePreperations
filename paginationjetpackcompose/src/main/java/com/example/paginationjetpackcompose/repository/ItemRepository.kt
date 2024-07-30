package com.example.paginationjetpackcompose.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paginationjetpackcompose.models.Item
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow

class ItemRepository {

    fun getItemsStream(): Flow<PagingData<Item>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { ItemPagingSource() }
        ).flow
    }
}

class ItemPagingSource : PagingSource<Int, Item>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        delay(1222)
        val page = params.key ?: 0
        val items = List(20) { Item(it + page * 20, "Item ${it + page * 20}") }
        return LoadResult.Page(
            data = items,
            prevKey = if (page == 0) null else page - 1,
            nextKey = if (items.isEmpty()) null else page + 1
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        return state.anchorPosition
    }
}