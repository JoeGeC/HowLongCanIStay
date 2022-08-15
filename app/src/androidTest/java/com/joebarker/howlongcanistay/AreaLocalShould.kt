package com.joebarker.howlongcanistay

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.joebarker.howlongcanistay.local.AreaLocal
import com.joebarker.howlongcanistay.local.LocalArea
import com.joebarker.howlongcanistay.local.AreaDatabase
import org.junit.Assert.assertEquals
import org.junit.Test

class AreaLocalShould {
    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val database = Room.inMemoryDatabaseBuilder(
        context, AreaDatabase::class.java).build()
    private val dao = database.areasDao()
    private val local = AreaLocal(context, database)

    @Test
    fun addNewAreaToDatabase(){
        val areaName = "sdf"
        val daysAllowed = 5
        val period = 6
        val area = LocalArea(areaName, daysAllowed, period)
        local.addArea(areaName, daysAllowed, period)
        assertEquals(area, dao.findByName(areaName))
    }

    @Test
    fun getAllAreasFromDatabase(){
        val areaName1 = "sad"
        val daysAllowed1 = 2
        val period1 = 4
        val areaName2 = "asdf"
        val daysAllowed2 = 4
        val period2 = 7
        dao.insertAll(LocalArea(areaName1, daysAllowed1, period1), LocalArea(areaName2, daysAllowed2, period2))
        val areaItemModels = listOf(AreaItemModel(areaName1, daysAllowed1, period1), AreaItemModel(areaName2, daysAllowed2, period2))
        assertEquals(local.getAreas(), areaItemModels)
    }

}