package com.example.basiclayoutpractice.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.basiclayoutpractice.R
import com.example.basiclayoutpractice.models.UserModel

@Composable
fun UserRowWithThumbImg(modifier: Modifier = Modifier) {
    val userModel = UserModel(R.drawable.bash, "Alfred Sisley", "3 minutes ago")

    Box(modifier = modifier.padding(10.dp)) {
        Column(
            modifier = modifier
                .border(width = 0.5.dp, color = Color.Black)
                .fillMaxWidth()
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Image(
                    painterResource(id = userModel.img),
                    contentDescription = "artist",
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(100)
                        )
                        .size(70.dp)
                )
                Column {
                    Text(
                        text = userModel.name,
                        style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    )
                    Text(text = userModel.lastSeen)
                }
            }

            Image(
                painter = painterResource(id = R.drawable.thumb),
                contentDescription = "ThumbImage"
            )
        }
    }
}