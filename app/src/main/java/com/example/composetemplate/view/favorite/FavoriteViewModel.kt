package com.example.composetemplate.view.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composetemplate.data.models.local.CoinMarket
import com.example.composetemplate.data.models.local.ResultWrapper
import com.example.composetemplate.data.repository.coin.market.CoinMarketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val coinMarketRepository: CoinMarketRepository) :
    ViewModel() {

    private val _state: MutableLiveData<FavoriteUiState> = MutableLiveData(FavoriteUiState())
    val state: LiveData<FavoriteUiState> get() = _state

    fun getCoins(
        currency: String = "usd",
        order: String = "market_cap_desc",
        perPage: Int = 100,
        page: Int = 1,
        sparkline: Boolean = false,
        locale: String = "en"
    ) {
        viewModelScope.launch {
            coinMarketRepository.getCoins(
                currency = currency,
                order = order,
                perPage = perPage,
                page = page,
                sparkline = sparkline,
                locale = locale
            )
                .collect { result ->
                    when (result) {
                        is ResultWrapper.Value -> {
                            _state.postValue(
                                FavoriteUiState(
                                    coinsMarket = result.value,
                                    isLoading = false
                                )
                            )
                        }
                        is ResultWrapper.Error -> {
                            _state.postValue(
                                FavoriteUiState(
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

data class FavoriteUiState(
    val error: String? = null,
    val coinsMarket: List<CoinMarket>? = null,
    val isLoading: Boolean = true
)