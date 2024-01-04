package com.example.basiclayoutpractice.widgets.flowlayoutswidgets

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class FlowRowDataModel(val height: Dp, val width: Dp, val color: Color)

val listOfFlowRowItem = listOf(
    FlowRowDataModel(40.dp, 40.dp, Color.Blue),
    FlowRowDataModel(40.dp, 60.dp, Color.Magenta),
    FlowRowDataModel(40.dp, 80.dp, Color.Yellow),
    FlowRowDataModel(40.dp, 15.dp, Color.Cyan),
    FlowRowDataModel(40.dp, 100.dp, Color.Black),
    FlowRowDataModel(40.dp, 50.dp, Color.Green),
    FlowRowDataModel(40.dp, 70.dp, Color.Red),
    FlowRowDataModel(40.dp, 20.dp, Color.LightGray),
    FlowRowDataModel(40.dp, 30.dp, Color.Yellow),
    FlowRowDataModel(40.dp, 80.dp, Color.DarkGray),
    FlowRowDataModel(40.dp, 70.dp, Color.Gray),
    FlowRowDataModel(40.dp, 60.dp, Color.Red),
    FlowRowDataModel(40.dp, 40.dp, Color.Black),

    )