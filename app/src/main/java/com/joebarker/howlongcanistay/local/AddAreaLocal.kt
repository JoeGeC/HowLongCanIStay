package com.joebarker.howlongcanistay.local

import com.joebarker.howlongcanistay.repository.AddAreaRepository

class AddAreaLocal(private val database: AreaDatabase) : AddAreaRepository {
    override fun addArea(areaName: String, daysAllowed: Int, period: Int) {
        database.areasDao().insertAll(Area(areaName, daysAllowed, period))
    }
}