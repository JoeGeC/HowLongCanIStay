package com.joebarker.howlongcanistay.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocalArea(
    @PrimaryKey val name: String,
    @ColumnInfo(name = "days_allowed") val days_allowed: Int?,
    @ColumnInfo(name = "period") val period: Int?
)