package com.example.vaultspayrevamp.screens.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vaultspayrevamp.R
import com.example.vaultspayrevamp.screens.commonwidgets.CommonTextView
import com.example.vaultspayrevamp.screens.commonwidgets.CommonVectorImage
import com.example.vaultspayrevamp.screens.commonwidgets.CommonVectorImageWithClick
import com.example.vaultspayrevamp.screens.commonwidgets.KycStatusSection
import com.example.vaultspayrevamp.ui.theme.urbanist_bold
import com.example.vaultspayrevamp.ui.theme.urbanist_semi_bold

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

                CommonVectorImageWithClick(
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

            //wallet section

            Row(
                modifier = modifier
                    .padding(top = 10.dp)
                    .height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Row(
                    modifier = modifier
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp)
                        )
                        .clip(RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp))
                        .clickable { }
                        .padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    CommonVectorImage(
                        icon = R.drawable.dirham,
                        size = DpSize(width = 18.dp, height = 18.dp)
                    )
                    CommonTextView(label = "AED", fontFamily = urbanist_bold, size = 9.sp)
                    CommonVectorImage(
                        icon = R.drawable.open_presentation_sheet_icon_light,
                        size = DpSize(width = 10.dp, height = 10.dp)
                    )
                }

//account number
                Row(
                    modifier = modifier
                        .fillMaxHeight()
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp)
                        )
                        .clip(RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp))
                        .clickable { }
                        .padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {

                    CommonTextView(
                        label = "Acc No. 1000971123456789",
                        fontFamily = urbanist_bold,
                        size = 9.sp,
                        color = R.color.grey_text
                    )
                    CommonVectorImage(
                        icon = R.drawable.path_8520,
                        size = DpSize(width = 16.dp, height = 16.dp)
                    )
                }


            }
            //kyc section
            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                modifier = modifier.padding(top = 12.dp)
            ) {
                KycStatusSection(color = colorResource(id = R.color.green))
                KycStatusSection(
                    color = colorResource(id = R.color.blue),
                    label = "Merchant Account", icon = R.drawable.individual_user_small_icon
                )
            }

            //loged in location
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.padding(top = 12.dp)
            ) {
                CommonTextView(
                    label = "Logged in from",
                    fontFamily = urbanist_semi_bold,
                    size = 9.sp, color = R.color.grey_text
                )
                Spacer(modifier = modifier.width(8.dp))
                CommonVectorImage(
                    icon = R.drawable.location_icon_light,
                    size = DpSize(width = 9.dp, height = 9.dp),
                    modifier = modifier.padding(end = 3.dp)
                )
                CommonTextView(
                    label = "Dubai",
                    fontFamily = urbanist_semi_bold,
                    size = 9.sp, color = R.color.grey_text
                )
            }



        }
    }

}

@Preview
@Composable
fun PreviewSettingsScreen() {
    SettingsScreen()
}