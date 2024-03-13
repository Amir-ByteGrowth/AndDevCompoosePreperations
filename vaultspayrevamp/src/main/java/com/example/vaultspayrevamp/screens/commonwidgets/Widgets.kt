package com.example.vaultspayrevamp.screens.commonwidgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vaultspayrevamp.R
import com.example.vaultspayrevamp.ui.theme.urbanist_bold

@Composable
fun CommonTextView(
    label: String,
    fontFamily: FontFamily,
    color: Int = R.color.black,
    size: TextUnit,
    modifier: Modifier = Modifier
) {
    Text(text = label, fontFamily = fontFamily, fontSize = size, color = colorResource(id = color), modifier = modifier)
}

@Composable
fun CommonVectorImageWithClick(
    icon: Int,
    modifier: Modifier = Modifier,
    size: DpSize,
    click: () -> Unit
) {
    Image(
        imageVector = ImageVector.vectorResource(icon),
        contentDescription = "Close", modifier = modifier
            .size(size)
            .clickable { click.invoke() }
    )
}

@Composable
fun CommonVectorImage(icon: Int, modifier: Modifier = Modifier, size: DpSize) {
    Image(
        imageVector = ImageVector.vectorResource(icon),
        contentDescription = "Close", modifier = modifier
            .size(size)

    )
}

@Composable
fun KycStatusSection(
    modifier: Modifier = Modifier,
    color: Color = Color.Green,
    icon: Int = R.drawable.verified_icon,
    label: String= "KYC Verified"
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(color)
            .padding(vertical = 5.dp, horizontal = 7.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = "icon",
            modifier = modifier.size(14.dp)
        )

        CommonTextView(
            label =label,
            fontFamily = urbanist_bold,
            size = 9.sp,
            color = R.color.white
        )
    }
}

@Preview
@Composable
fun Preview() {
    KycStatusSection()
}
