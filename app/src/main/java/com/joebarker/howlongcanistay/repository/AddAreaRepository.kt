package com.joebarker.howlongcanistay.repository

interface AddAreaRepository {
    fun addArea(areaName: String, daysAllowed: Int, period: Int)
}
