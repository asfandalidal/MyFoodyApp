package com.example.foodyapp.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodyTopBar() {
    Surface(
        shape = MaterialTheme.shapes.small,
        color = Color.White,
        shadowElevation = 4.dp
    ) {
        TopAppBar(
            title = {
                Text("Foodium", style = MaterialTheme.typography.headlineMedium, color = Color.Black)
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White),
            modifier = Modifier.fillMaxWidth()
        )
    }
}
