package com.example.composetemplate.view.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.composetemplate.data.models.local.Exchange
import com.example.composetemplate.data.models.local.ResultWrapper
import com.example.composetemplate.data.models.local.Trend
import com.example.composetemplate.data.repository.exchange.ExchangeRepository
import com.example.composetemplate.data.repository.trend.TrendRepository
import com.example.composetemplate.di.IoDispatcher
import com.example.composetemplate.di.MainDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val trendRepository: TrendRepository,
    private val exchangeRepository: ExchangeRepository,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _state = MutableStateFlow<HomeUiState>(HomeUiState())
    val state get() = _state

    fun getTrends() {
        viewModelScope.launch(mainDispatcher) {
            trendRepository.getTrending().collect { resultWrapper ->
                when (resultWrapper) {
                    is ResultWrapper.Value -> {
                        _state.update { homeUiState ->
                            homeUiState.copy(
                                trends = resultWrapper.value?.filterNotNull(),
                                isLoadingTrendList = false
                            )
                        }
                    }
                    is ResultWrapper.Error -> {
                        _state.update { homeUiState ->
                            homeUiState.copy(
                                error = resultWrapper.error.message,
                                isLoadingTrendList = false
                            )
                        }
                    }
                }
            }
        }
    }

    fun getExchange(perPage: Int, page: Int): Flow<PagingData<Exchange>> =
        exchangeRepository.getExchange(perPage = perPage, page = page)
            .cachedIn(viewModelScope)
}

data class HomeUiState(
    val error: String? = null,
    val trends: List<Trend>? = null,
    val isLoadingTrendList: Boolean = true
)