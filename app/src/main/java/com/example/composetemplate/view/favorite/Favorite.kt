package com.example.composetemplate.view.favorite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.composetemplate.data.models.local.CoinMarket
import com.example.composetemplate.view.base.Section

@Composable
fun FavoriteScreen(viewModel: FavoriteViewModel, onItemClicked: (String) -> Unit) {

    val favoriteUiState by viewModel.state.observeAsState()

    LaunchedEffect(key1 = true) {
        viewModel.getCoins()
    }

    favoriteUiState?.let { favoriteUiState ->
        Section(title = "Coin Markets", isLoading = favoriteUiState.isLoading) {
            favoriteUiState.coinsMarket?.let { listCoins ->
                LazyColumn(modifier = Modifier.padding(10.dp)) {
                    items(listCoins) {
                        CoinMarketItem(coinMarketItem = it, onItemClicked = onItemClicked)
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun CoinMarketItem(
    modifier: Modifier = Modifier,
    coinMarketItem: CoinMarket,
    onItemClicked: (String) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = 10.dp
    ) {
        Row(
            modifier = modifier
                .padding(10.dp)
                .clickable(onClick = { onItemClicked(coinMarketItem.id ?: "") })
        ) {
            Box(
                modifier = modifier
                    .clip(RoundedCornerShape(12.dp, 0.dp, 0.dp, 12.dp))
                    .weight(0.2f)
            ) {
                AsyncImage(model = coinMarketItem.image, contentDescription = null)
            }
            Spacer(modifier = modifier.width(10.dp))
            Column(modifier = modifier.weight(1f)) {
                Text(text = coinMarketItem.name ?: "")
                Spacer(modifier = modifier.height(10.dp))
                Text(text = coinMarketItem.symbol ?: "")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCoinMarketItem() {

}