package com.example.composetemplate.view.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetemplate.ui.theme.ComposeTemplateTheme

@Composable
fun Section(
    modifier: Modifier = Modifier,
    title: String,
    isLoading: Boolean,
    content: @Composable () -> Unit
) {
    val strokeWidth = 5.dp
    Column(modifier = modifier
        .padding(10.dp)
        .heightIn(100.dp)) {
        Spacer(modifier = modifier.height(10.dp))
        Text(text = title, style = MaterialTheme.typography.h6)
        Spacer(modifier = modifier.width(10.dp))
        if (!isLoading)
            content()
        else
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp), horizontalArrangement = Arrangement.Center) {
                CircularProgressIndicator(
                    color = Color.LightGray,
                    strokeWidth = strokeWidth
                )
            }
    }
}

@Preview(showBackground = true)
@Composable
fun previewSectionLoading() {
    ComposeTemplateTheme {
        Section(title = "new title", isLoading = true) {
            Text(text = "jdjsgs")
        }
    }
}