package com.joebarker.howlongcanistay.repository

import com.joebarker.howlongcanistay.AreaItemModel

interface AreaRepository {
    fun addArea(areaName: String, daysAllowed: Int, period: Int)
    fun getAreas(): List<AreaItemModel>
}
