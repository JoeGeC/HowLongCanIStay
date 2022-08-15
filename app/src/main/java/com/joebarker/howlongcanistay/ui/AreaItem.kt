package com.joebarker.howlongcanistay.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joebarker.howlongcanistay.ui.theme.Purple200

@Composable
fun AreaItem(name: String, daysRemaining: Int) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .border(2.dp, Purple200)
        .clip(RoundedCornerShape(8.dp))
        .padding(8.dp)
    ){
        Text(name, fontSize = 21.sp)
        Text("Days remaining: $daysRemaining/180", fontSize = 16.sp)
    }
}

@Preview()
@Composable
fun AreaItem() { AreaItem(name = "Schengen Area", daysRemaining = 90) }