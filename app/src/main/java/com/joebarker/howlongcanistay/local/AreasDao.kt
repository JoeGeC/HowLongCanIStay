package com.joebarker.howlongcanistay.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.joebarker.howlongcanistay.AreaItemModel

@Dao
interface AreasDao {
    @Query("SELECT * FROM localarea WHERE name LIKE :name LIMIT 1")
    fun findByName(name: String): LocalArea

    @Insert
    fun insertAll(vararg area: LocalArea)

    @Delete
    fun delete(area: LocalArea)

    @Query("SELECT * FROM localarea")
    fun getAll(): List<LocalArea>
}