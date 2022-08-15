package com.joebarker.howlongcanistay

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.joebarker.howlongcanistay.local.AddAreaLocal
import com.joebarker.howlongcanistay.local.Area
import com.joebarker.howlongcanistay.local.AreaDatabase
import org.junit.Assert.assertEquals
import org.junit.Test

class AddAreaLocalShould {
    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val database = Room.inMemoryDatabaseBuilder(
        context, AreaDatabase::class.java).build()
    private val dao = database.areasDao()
    private val local = AddAreaLocal(database)

    @Test
    fun addNewAreaToDatabase(){
        val areaName = "sdf"
        val daysAllowed = 5
        val period = 6
        val area = Area(areaName, daysAllowed, period)
        local.addArea(areaName, daysAllowed, period)
        assertEquals(area, dao.findByName(areaName))
    }

}