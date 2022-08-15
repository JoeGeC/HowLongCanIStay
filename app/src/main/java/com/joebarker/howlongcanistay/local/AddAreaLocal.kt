package com.joebarker.howlongcanistay.local

import android.content.Context
import androidx.room.Room
import com.joebarker.howlongcanistay.AreaItemModel
import com.joebarker.howlongcanistay.repository.AddAreaRepository

class AddAreaLocal(
    context: Context,
    private val database: AreaDatabase = Room.databaseBuilder(context, AreaDatabase::class.java, "area_database").build()
) : AddAreaRepository {

    override fun addArea(areaName: String, daysAllowed: Int, period: Int) {
        database.areasDao().insertAll(LocalArea(areaName, daysAllowed, period))
    }

    override fun getAreas(): List<AreaItemModel> {
        TODO("Not yet implemented")
    }
}