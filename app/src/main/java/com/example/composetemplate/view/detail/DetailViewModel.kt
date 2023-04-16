package com.example.composetemplate.view.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composetemplate.data.models.local.CoinMarket
import com.example.composetemplate.data.models.local.CoinMarketDetail
import com.example.composetemplate.data.models.local.ResultWrapper
import com.example.composetemplate.data.repository.coin.market.CoinMarketRepository
import com.example.composetemplate.view.favorite.FavoriteUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val coinMarketRepository: CoinMarketRepository) :
    ViewModel() {

    private val _state: MutableLiveData<DetailsUiState> = MutableLiveData(DetailsUiState())
    val state: LiveData<DetailsUiState> get() = _state

    fun getDetails(id: String) {
        viewModelScope.launch {
            coinMarketRepository.getCoinMarketDetails(id = id)
                .collect { result ->
                    when (result) {
                        is ResultWrapper.Value -> {
                            _state.postValue(
                                DetailsUiState(
                                    detail = result.value,
                                    isLoading = false
                                )
                            )
                        }
                        is ResultWrapper.Error -> {
                            _state.postValue(
                                DetailsUiState(
                                    error = result.error.message,
                                    isLoading = false
                                )
                            )
                        }
                    }
                }
        }
    }
}

data class DetailsUiState(
    val error: String? = null,
    val detail: CoinMarketDetail? = null,
    val isLoading: Boolean = true
)