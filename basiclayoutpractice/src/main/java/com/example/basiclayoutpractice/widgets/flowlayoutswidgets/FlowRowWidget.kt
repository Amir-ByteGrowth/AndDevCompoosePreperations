package com.example.basiclayoutpractice.widgets.flowlayoutswidgets


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FlowRowWidget() {
    FlowRow(
        modifier = Modifier.padding(1.dp),
        horizontalArrangement = Arrangement.spacedBy(1.dp),
    ) {
        repeat(10) { i ->
            val itemModifier = Modifier
                .padding(1.dp)
                .size(100.dp)
                .weight(1.5f)
                .clip(RoundedCornerShape(4.dp))
                .background(Color(0xFF9681EB))
            Spacer(modifier = itemModifier)
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FlowRowItemsWidget(){
    FlowRow(horizontalArrangement = Arrangement.Center ) {
        repeat(listOfFlowRowItem.size){
            index ->
            var item=listOfFlowRowItem.get(index)
            val itemModifier = Modifier
                .padding(horizontal = 5.dp, vertical = 3.dp)
                .size(width = item.width, height =item.height )
                .clip(RoundedCornerShape(4.dp))
                .background(item.color)
            Spacer(modifier = itemModifier)
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun flowGridView(rows: Int, columns: Int) {
    FlowColumn(
        modifier = Modifier.padding(1.dp),
        verticalArrangement = Arrangement.spacedBy(1.dp),
    ) {
        repeat(rows) {
            val itemModifier = Modifier
                .padding(1.dp)
                .fillMaxWidth()
                .weight(1f)
                .clip(RoundedCornerShape(4.dp))

            Box(modifier = itemModifier) {
                FlowRow(
                    modifier = Modifier.padding(1.dp),
                    horizontalArrangement = Arrangement.spacedBy(1.dp),
                ) {
                    repeat(columns) {i ->
                        val itemModifier = Modifier
                            .padding(1.dp)
                            .fillMaxHeight()
                            .weight(1f)
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color(0xFF9681EB))
                        Spacer(modifier = itemModifier)
                    }
                }
            }

        }
    }
}