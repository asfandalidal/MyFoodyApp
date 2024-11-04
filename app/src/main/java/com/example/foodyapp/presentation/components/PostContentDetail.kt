package com.example.foodyapp.presentation.components


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.example.foodyapp.model.FoodItemEntity


@Composable
fun DetailContentScreen(
    foodItem: FoodItemEntity
) {
    val scroll = rememberScrollState(0)
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                model = foodItem.imageUrl,
                onError = {
                    Log.e("ImageError", "Failed to load image from URL:")
                }
            ),
            contentDescription = null,
            modifier = Modifier
                .width(500.dp)
                .height(200.dp),
            contentScale = ContentScale.FillWidth,
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = foodItem.title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(4.dp)
        ) {
            Image(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 4.dp)
                    .size(28.dp)
                    .offset(y = (-3).dp)
            )

            Text(
                text = foodItem.author,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 4.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Start
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        LazyColumn(
            Modifier.padding(5.dp)
        ) {
            item {
                Text(
                    text = foodItem.body,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(350.dp)
                        .padding(start = 5.dp)
                        .verticalScroll(scroll),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}
