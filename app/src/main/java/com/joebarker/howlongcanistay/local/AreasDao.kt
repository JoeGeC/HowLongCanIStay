package com.joebarker.howlongcanistay.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AreasDao {
    @Query("SELECT * FROM localarea WHERE name LIKE :name LIMIT 1")
    fun findByName(name: String): LocalArea

    @Insert
    fun insertAll(vararg area: LocalArea)

    @Delete
    fun delete(area: LocalArea)
}