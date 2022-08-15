package com.joebarker.howlongcanistay

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.joebarker.howlongcanistay.local.AddAreaLocal
import com.joebarker.howlongcanistay.ui.AddAreaItem
import com.joebarker.howlongcanistay.ui.AreaItem
import com.joebarker.howlongcanistay.ui.theme.HowLongCanIStayTheme
import com.joebarker.howlongcanistay.viewModels.MainViewModel

class MainActivity : ComponentActivity() {
    private val viewModel = MainViewModel(AddAreaLocal(this))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HowLongCanIStayTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LazyColumn(Modifier.fillMaxHeight().fillMaxWidth()){
                        items(listOf(AreaItemModel("Schengen Area", 90, 180))) { area ->
                            AreaItem(area.name, area.daysAllowed)
                        }
                        item { AddAreaItem(viewModel) }
                    }
                }
            }
        }
    }
}