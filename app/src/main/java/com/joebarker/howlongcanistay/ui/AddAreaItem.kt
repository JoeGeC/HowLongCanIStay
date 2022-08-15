package com.joebarker.howlongcanistay.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joebarker.howlongcanistay.ui.theme.Purple200
import com.joebarker.howlongcanistay.viewModels.MainViewModel

@Composable
fun AddAreaItem(viewModel: MainViewModel) {
    var areaName by rememberSaveable { mutableStateOf("") }
    var daysAllowed by rememberSaveable { mutableStateOf("") }
    var period by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(2.dp, Purple200)
            .clip(RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        Text("New Area", modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp), fontSize = 21.sp)
        TextField(
            areaName,
            onValueChange = { areaName = it },
            label = { Text("Area Name") },
            modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp).fillMaxWidth(),
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            TextField(
                daysAllowed,
                onValueChange = { daysAllowed = it },
                label = { Text("Days allowed") },
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Text("/", Modifier.weight(0.1f), fontSize = 31.sp, textAlign = TextAlign.Center)
            TextField(
                period,
                onValueChange = { period = it },
                label = { Text("Period") },
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }
        Text(
            "Save",
            Modifier
                .fillMaxWidth()
                .clickable { viewModel.addNewArea(areaName, daysAllowed, period) },
            textAlign = TextAlign.End,
            fontSize = 16.sp
        )
    }
}

