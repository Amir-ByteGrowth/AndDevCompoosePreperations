package com.example.basiclayoutpractice.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.basiclayoutpractice.models.listOfTitleSubTitle


@Composable
fun CardWithLandPortraitBoCons() {
    BoxWithConstraints {
        val boxWithConstraintsScope = this
        LazyRow(
            contentPadding = PaddingValues(24.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(listOfTitleSubTitle) { item ->

                if (boxWithConstraintsScope.maxWidth > maxHeight) {
                    // in landscape mode
                    val cardWidth = boxWithConstraintsScope.maxWidth / 4
                    ItemCard(
                        title = item.title,
                        subTitle = item.subTitle,
                        cardHeight = boxWithConstraintsScope.maxHeight / 3,
                        cardWidth = cardWidth - cardWidth * 0.15f
                    )
                } else {
                    // in portrait mode
                    val cardWidth = boxWithConstraintsScope.maxWidth / 2
                    ItemCard(
                        title = item.title,
                        subTitle = item.subTitle,
                        cardHeight = boxWithConstraintsScope.maxHeight / 4,
                        cardWidth = cardWidth - cardWidth * 0.2f
                    )
                }
            }
        }
    }
}


@Composable
fun ItemCard(title: String, subTitle: String, cardWidth: Dp, cardHeight: Dp) {
    Card(
        modifier = Modifier
            .width(cardWidth)
            .height(cardHeight)
            .padding(10.dp),
        elevation = cardElevation(1.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Black)
    ) {
        Column(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = TextStyle.Default.copy(
                    color = Color.White,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize
                )
            )

            Text(
                text = subTitle,
                style = TextStyle.Default.copy(
                    color = Color.White,
                    fontSize = MaterialTheme.typography.bodySmall.fontSize
                )
            )
        }
    }
}

@Preview()
@Composable
fun ItemCardPrev() {
    ItemCard("Title 1", "Subtitle 1", 100.dp, 150.dp)
}