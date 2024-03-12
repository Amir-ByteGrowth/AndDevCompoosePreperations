package com.example.vaultspayrevamp.screens.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vaultspayrevamp.R
import com.example.vaultspayrevamp.screens.commonwidgets.CommonTextView
import com.example.vaultspayrevamp.screens.commonwidgets.CommonVectorImage
import com.example.vaultspayrevamp.ui.theme.urbanist_bold

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.settings_back))
    ) {
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Spacer(modifier = modifier.height(30.dp))
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CommonTextView(label = "Settings", fontFamily = urbanist_bold, size = 18.sp)

                CommonVectorImage(
                    icon = R.drawable.close_light,
                    size = DpSize(width = 30.dp, height = 30.dp),
                    click = {})
            }

            Image(
                painter = painterResource(id = R.drawable.dp),
                contentDescription = "Dp",
                modifier = modifier
                    .padding(top = 50.dp)
                    .size(100.dp)

            )

            CommonTextView(
                label = "Mohammed Razeen C N",
                fontFamily = urbanist_bold,
                size = 12.sp,
                modifier = modifier.padding(top = 15.dp)
            )

        }
    }

}

@Preview
@Composable
fun PreviewSettingsScreen() {
    SettingsScreen()
}