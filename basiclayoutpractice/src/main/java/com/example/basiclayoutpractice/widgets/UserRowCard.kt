package com.example.basiclayoutpractice.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
fun UserRowCard(modifier: Modifier = Modifier) {
    val userModel = UserModel(R.drawable.bash, "Alfred Sisley", "3 minutes ago")

    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = modifier
            .fillMaxWidth()
            .height(110.dp)
            .padding(horizontal = 10.dp, vertical = 10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        shape = RoundedCornerShape(0.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            Box(contentAlignment = Alignment.BottomEnd) {
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
                Icon(Icons.Filled.CheckCircle, contentDescription = "Icon", tint = Color.Green)
            }
            Column {
                Text(
                    text = userModel.name,
                    style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold)
                )
                Text(text = userModel.lastSeen)
            }
        }
    }
}