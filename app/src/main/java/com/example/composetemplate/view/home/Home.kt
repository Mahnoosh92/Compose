package com.example.composetemplate.view.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import com.example.composetemplate.data.models.local.Exchange
import com.example.composetemplate.data.models.local.Trend
import com.example.composetemplate.ui.theme.ComposeTemplateTheme
import com.example.composetemplate.view.base.Section
import com.example.composetemplate.view.home.paging.PER_PAGE

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    LaunchedEffect(key1 = true) {
        viewModel.getTrends()
    }

    val exchanges = viewModel.getExchange(perPage = PER_PAGE, page = 1).collectAsLazyPagingItems()

    val homeUiState by viewModel.state.collectAsState()

    val showButtonDerive by remember {
        derivedStateOf {
            isLoading(exchanges.loadState.append)
        }
    }

    Column() {
        Section(title = "Trending Coins", isLoading = homeUiState.isLoadingTrendList) {
            homeUiState.trends?.let { trendList ->
                LazyRow() {
                    items(trendList) {
                        Surface(modifier = Modifier.padding(end = 8.dp)) {
                            TrendItem(item = it) {}
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Section(title = "Top coins", isLoading = showButtonDerive) {
            Log.i("mahnoosh", "HomeScreen: $showButtonDerive")
            LazyColumn() {
                items(
                    items = exchanges
                ) { exchange ->
                    ExchangeItem(item = exchange)
                }
            }
        }
    }
}

fun isLoading(loadState: LoadState): Boolean {
    return when (loadState) { // Pagination
        is LoadState.Error -> {
            false
        }
        is LoadState.Loading -> {
            true
        }
        else -> {
            false
        }
    }
}

@Composable
fun ExchangeItem(modifier: Modifier = Modifier, item: Exchange?) {
    item?.let {
        Card(
            modifier = modifier
                .padding(vertical = 8.dp, horizontal = 6.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            elevation = 10.dp
        ) {
            Column(modifier = modifier.padding(10.dp)) {
                Text(text = item.name ?: "", style = MaterialTheme.typography.subtitle1)
                Spacer(modifier = modifier.height(10.dp))
                Text(text = item.country ?: "", style = MaterialTheme.typography.subtitle2)
                Spacer(modifier = modifier.height(10.dp))
                Text(
                    text = item.description ?: "",
                    style = MaterialTheme.typography.caption,
                    maxLines = 2
                )
            }
        }
    }
}

@Composable
fun TrendItem(modifier: Modifier = Modifier, item: Trend, trendItemClicked: () -> Unit) {

    Box(
        modifier = modifier
            .width(150.dp)
            .aspectRatio(1.5f)
            .clip(RoundedCornerShape(12))
            .background(Color.Black.copy(alpha = 0.3F))
    ) {
        Column(
            modifier = modifier
                .padding(15.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = modifier.clip(CircleShape), contentAlignment = Alignment.Center
            ) {
                AsyncImage(model = item.small, contentDescription = null)
            }
            Spacer(modifier = modifier.height(15.dp))
            Text(text = item.name ?: "")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTrendItem() {
    ComposeTemplateTheme {
        TrendItem(
            item = Trend(
                id = "tominet",
                coinId = 28730,
                name = "tomiNet",
                symbol = "TOMI",
                marketCapRank = 254,
                thumb = "https://assets.coingecko.com/coins/images/28730/thumb/logo_for_token.png?1673690005",
                small = "https://assets.coingecko.com/coins/images/28730/small/logo_for_token.png?1673690005",
                large = "https://assets.coingecko.com/coins/images/28730/large/logo_for_token.png?1673690005",
                slug = "tominet",
                priceBtc = 0.0000793878423189642F,
                score = 1
            )
        ) {

        }
    }
}