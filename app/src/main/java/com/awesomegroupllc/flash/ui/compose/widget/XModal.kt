package com.awesomegroupllc.flash.ui.compose.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.awesomegroupllc.flash.R

@Composable
fun XModal(
    onClose: () -> Unit,
    content: @Composable () -> Unit
) {
    val intSrc = remember { MutableInteractionSource() }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.3f))
            .clickable(intSrc, null) {  } // To capture clicks invisibly
            .padding(32.dp)
    ) {
        Box(
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .width(IntrinsicSize.Min)
                .heightIn(min = 300.dp)
                .widthIn(min = 250.dp)
                .align(Alignment.Center)
                .border(
                    3.dp,
                    colorResource(id = R.color.blue_light),
                    shape = RoundedCornerShape(8.dp)
                )
                .clip(RoundedCornerShape(8.dp))
        ) {
            Image(
                painter = painterResource(id = R.drawable.bg_main),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Surface(
                modifier = Modifier
                    .padding(top = 36.dp, end = 12.dp, start = 12.dp, bottom = 12.dp)
                    .align(Alignment.Center),
                contentColor = Color.White,
                color = Color.Transparent
            ) {
                content()
            }

            Icon(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(48.dp)
                    .clickable { onClose() }
                    .padding(12.dp),
                painter = painterResource(id = com.google.android.material.R.drawable.ic_m3_chip_close),
                contentDescription = "Close",
                tint = Color.White
            )
        }
    }
}


@Preview
@Composable
fun PreviewXModal() {
    XModal({}) {
        Text(text = "Testing")
    }
}