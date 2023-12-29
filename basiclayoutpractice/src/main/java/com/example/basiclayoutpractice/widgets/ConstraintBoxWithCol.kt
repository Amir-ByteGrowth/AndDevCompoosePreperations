package com.example.basiclayoutpractice.widgets


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.basiclayoutpractice.models.listOfImages

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConstraintBoxWithCol(thumbnails: List<String> = listOfImages, modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = modifier.padding(10.dp), elevation = CardDefaults.cardElevation(1.dp)

    ) {
        Column(Modifier.padding(15.dp)) {
            Text(
                text = "Martin Buber",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    color = Color.Black
                )
            )

            Text(
                text = "An animal's eye have the power to speak a great language",
                style = TextStyle.Default.copy(color = Color.Black, fontSize = 12.sp)
            )

            Spacer(
                modifier = modifier
                    .padding(vertical = 15.dp)
                    .background(Color.LightGray)
                    .height(1.5.dp)
                    .fillMaxWidth()

            )
            BoxWithConstraints(modifier=modifier.padding(top = 10.dp)) {
                val boxWithConstraintsScope: BoxWithConstraintsScope = this
                val padding = 10.dp
                val thumbnailSize = 60.dp
                val numberOfThumbnailsToShow = maxOf(
                    0,
                    boxWithConstraintsScope.maxWidth.div(padding + thumbnailSize).toInt().minus(1)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(padding),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    thumbnails.take(numberOfThumbnailsToShow).forEach { url ->
                        Image(
                            painter = rememberImagePainter(data = url),
                            contentDescription = null,
                            modifier = Modifier
                                .width(thumbnailSize)
                                .aspectRatio(1f),
                        )
                    }

                    val remaining = thumbnails.size - numberOfThumbnailsToShow
                    if (remaining > 0) {
                        BadgedBox(
                            badge = {
                                Badge(containerColor = Color.Green) { Text("$remaining") }
                            }
                        ) {
                            //...
                        }
                    }
                }
            }

        }
    }


}