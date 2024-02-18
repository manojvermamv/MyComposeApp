package com.plcoding.composepaging3caching.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.plcoding.composepaging3caching.ui.theme.Material3ComposeTheme


@Composable
fun EmptyScreen(
    content: String
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = content,
                fontSize = 20.sp
            )
        }
    }
}


@Preview
@Composable
fun EmptyScreenPreview() {
    Material3ComposeTheme {
        EmptyScreen(content = "Put your content here.")
    }
}