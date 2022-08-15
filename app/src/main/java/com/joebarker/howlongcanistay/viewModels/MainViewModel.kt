package com.joebarker.howlongcanistay.viewModels

import androidx.lifecycle.ViewModel
import com.joebarker.howlongcanistay.repository.AddAreaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.lang.Exception
import java.lang.NumberFormatException

class MainViewModel(
    private val repositoryMock: AddAreaRepository? = null
): ViewModel() {
    private val _error = MutableStateFlow("")
    val error: StateFlow<String> = _error

    fun addNewArea(areaName: String, daysAllowedAsString: String, periodAsString: String) {
        _error.value = getError(areaName, daysAllowedAsString, periodAsString)
        if(error.value.isNotEmpty()) return
        try{
            repositoryMock?.addArea(areaName, daysAllowedAsString.toInt(), periodAsString.toInt())
        } catch(e: Exception){
            _error.value = SomethingWentWrong
        }
    }

    private fun getError(areaName: String, daysAllowedAsString: String, periodAsString: String) : String {
        if (areaName.isBlank()) return AreaNameMustNotBeEmpty
        val daysAllowed = try { daysAllowedAsString.toInt() } catch (e: NumberFormatException) { return DaysMustBeNumerical }
        val period = try { periodAsString.toInt() } catch (e: NumberFormatException) { return PeriodMustBeNumerical }
        if (daysAllowed > period) return PeriodCannotBeLower
        return NoError
    }

    companion object {
        const val SomethingWentWrong = "Something went wrong"
        const val AreaNameMustNotBeEmpty = "Area name must not be empty"
        const val PeriodCannotBeLower = "Period cannot be lower than days allowed"
        const val DaysMustBeNumerical = "Days must be numerical"
        const val PeriodMustBeNumerical = "Period must be numerical"
        const val NoError = ""
    }
}