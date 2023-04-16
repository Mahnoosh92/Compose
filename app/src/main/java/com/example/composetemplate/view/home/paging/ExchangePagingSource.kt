package com.example.composetemplate.view.home.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.composetemplate.data.api.ApiService
import com.example.composetemplate.data.models.local.Exchange

const val PER_PAGE = 10

class ExchangePagingSource(
    private val apiService: ApiService,
) : PagingSource<Int, Exchange>() {
    override fun getRefreshKey(state: PagingState<Int, Exchange>): Int? {
//        return state.anchorPosition?.let { anchorPosition ->
//            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
//                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Exchange> {
        return try {
            val page = params.key ?: 1
            val response = apiService.getExchange(page = page, perPage = PER_PAGE)

            val exchanges = response.body()?.map {
                it.toExchange()
            }
            LoadResult.Page(
                data = exchanges ?: emptyList<Exchange>(),
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (exchanges == null) null else {
                    if (exchanges.isEmpty()) null else page.plus(1)
                },
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}