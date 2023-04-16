package com.example.composetemplate.view.detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.composetemplate.view.base.Section

@Composable
fun DetailScreen(detailType: String, viewModel: DetailViewModel) {

    LaunchedEffect(key1 = true) {
        viewModel.getDetails(detailType)
    }

    val detailUiState by viewModel.state.observeAsState()

    detailUiState?.let { detailUiState ->
        Section(title = "Details", isLoading = detailUiState.isLoading) {
            detailUiState.detail?.let {
                Text(text = it.description?.en ?: "salam")
            }
        }
    }
}