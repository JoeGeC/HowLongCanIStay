package com.joebarker.howlongcanistay.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LocalArea::class], version = 1)
abstract class AreaDatabase : RoomDatabase() {
    abstract fun areasDao(): AreasDao
}
