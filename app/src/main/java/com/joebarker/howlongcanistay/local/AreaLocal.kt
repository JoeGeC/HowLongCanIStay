package com.joebarker.howlongcanistay.local

import android.content.Context
import androidx.room.Room
import com.joebarker.howlongcanistay.AreaItemModel
import com.joebarker.howlongcanistay.repository.AreaRepository

class AreaLocal(
    context: Context,
    private val database: AreaDatabase = Room.databaseBuilder(context, AreaDatabase::class.java, "area_database").build()
) : AreaRepository {

    override fun addArea(areaName: String, daysAllowed: Int, period: Int) {
        database.areasDao().insertAll(LocalArea(areaName, daysAllowed, period))
    }

    override fun getAreas(): List<AreaItemModel> {
        return convert(database.areasDao().getAll())
    }

    private fun convert(localAreas: List<LocalArea>): List<AreaItemModel> {
        return localAreas.map { local ->
            AreaItemModel(local.name, local.days_allowed, local.period)
        }
    }
}