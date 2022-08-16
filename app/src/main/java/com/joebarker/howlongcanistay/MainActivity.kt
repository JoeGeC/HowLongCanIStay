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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.joebarker.howlongcanistay.local.AreaLocal
import com.joebarker.howlongcanistay.ui.AddAreaItem
import com.joebarker.howlongcanistay.ui.AreaItem
import com.joebarker.howlongcanistay.ui.theme.HowLongCanIStayTheme
import com.joebarker.howlongcanistay.viewModels.MainViewModel

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = MainViewModel(AreaLocal(this))
        setContent {
            val areas by viewModel.areas.collectAsState()
            HowLongCanIStayTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LazyColumn(
                        Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()){
                        items(areas) { area ->
                            AreaItem(area.name, area.daysAllowed, area.period)
                        }
                        item { AddAreaItem(viewModel) }
                    }
                }
            }
        }
    }
}