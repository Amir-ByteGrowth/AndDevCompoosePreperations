package com.example.vaultspayrevamp.screens.commonwidgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.vaultspayrevamp.R
import com.example.vaultspayrevamp.models.ItemType
import com.example.vaultspayrevamp.models.SettingsGenericItemModel
import com.example.vaultspayrevamp.models.accountSettingsList
import com.example.vaultspayrevamp.ui.theme.urbanist_bold
import com.example.vaultspayrevamp.ui.theme.urbanist_semi_bold

@Composable
fun SettingsGenericWidget(modifier: Modifier = Modifier, label: String = "Account Settings") {
    ConstraintLayout(modifier = modifier.fillMaxWidth()) {
        val (title, card) = createRefs()
        Text(
            text = label,
            fontFamily = urbanist_bold,
            color = Color.Black,
            fontSize = 12.sp,
            modifier = modifier.constrainAs(title) {
                top.linkTo(parent.top, 15.dp)
            })

        Card(
            modifier = modifier
                .fillMaxWidth()
                .constrainAs(card) {
                    top.linkTo(title.bottom, 10.dp)
                },
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {

                accountSettingsList.forEach { item ->
                    SettingsItem(genericItemModel = item)
                }

//                item {
//                    SettingsItem()
//                }

            }
        }


    }
}


@Composable
fun SettingsItem(modifier: Modifier = Modifier, genericItemModel: SettingsGenericItemModel) {
    ConstraintLayout(modifier = modifier.fillMaxWidth().padding(10.dp)) {
        val (iconId, titleId, detailId, forwardIconId, switchId) = createRefs()
        var startBarrier = createStartBarrier(forwardIconId, switchId)
        CommonVectorImage(
            icon = genericItemModel.icon,
            size = DpSize(width = 30.dp, height = 30.dp), modifier = modifier.constrainAs(iconId) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
            }
        )

        Column(modifier = modifier
            .constrainAs(titleId) {
                start.linkTo(iconId.end, 10.dp)
                top.linkTo(iconId.top)
                bottom.linkTo(iconId.bottom)
                end.linkTo(startBarrier, 10.dp)
                width = Dimension.fillToConstraints
            }) {
            CommonTextView(label = genericItemModel.title, fontFamily = urbanist_bold, size = 11.sp)
            genericItemModel.detail?.let {
                CommonTextView(
                    label = it,
                    fontFamily = urbanist_semi_bold,
                    size = 9.sp
                )
            }


        }

        if (genericItemModel.itemType == ItemType.MoveForward) {
            CommonVectorImage(
                icon = R.drawable.forward_icon,
                size = DpSize(width = 10.dp, height = 10.dp),
                modifier = modifier
                    .constrainAs(forwardIconId) {
                        end.linkTo(parent.end)
                        top.linkTo(iconId.top)
                        bottom.linkTo(iconId.bottom)
                    }

            )
        }


    }
}


//@Preview
//@Composable
//fun PreviewSettingGeneric() {
//    SettingsGenericWidget()
//}