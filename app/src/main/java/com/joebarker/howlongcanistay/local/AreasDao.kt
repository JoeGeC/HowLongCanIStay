package com.joebarker.howlongcanistay.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AreasDao {
    @Query("SELECT * FROM area WHERE name LIKE :name LIMIT 1")
    fun findByName(name: String): Area

    @Insert
    fun insertAll(vararg area: Area)

    @Delete
    fun delete(area: Area)
}