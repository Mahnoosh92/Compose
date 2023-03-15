package com.example.composetemplate.view.base


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetemplate.ui.theme.ComposeTemplateTheme

@Composable
fun BaseRow(modifier: Modifier = Modifier, color: Color, content: @Composable () -> Unit) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 8.dp)
    ) {
        Surface(modifier = modifier.size(60.dp, 60.dp), RoundedCornerShape(10.dp)) {
            Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = null, tint = color)
        }
        Spacer(modifier = modifier.width(10.dp))
        Row(modifier = Modifier.weight(1f)) {
            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBaseRow() {
    ComposeTemplateTheme {
        BaseRow(color = Color(0xFF004940)) {
            Column() {
                Text(text = "Hi")
            }
        }
    }
}