package com.joebarker.howlongcanistay

import android.database.sqlite.SQLiteConstraintException
import com.joebarker.howlongcanistay.repository.AreaRepository
import com.joebarker.howlongcanistay.viewModels.MainViewModel
import kotlinx.coroutines.Dispatchers
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class MainViewModelShould {
    private val areas = listOf(AreaItemModel("asd", 4, 5))
    private val repositoryMock = mock<AreaRepository> {
        on { getAreas() } doReturn areas
    }
    private val viewModel = MainViewModel(repositoryMock, Dispatchers.Unconfined)

    @Test
    fun showErrorIfDaysAllowedAreMoreThanPeriod() {
        viewModel.addNewArea("ds", "5", "4")
        assertEquals(MainViewModel.PeriodCannotBeLower, viewModel.error.value)
    }

    @Test
    fun showErrorIfDaysAllowedIsNotAnInt() {
        viewModel.addNewArea("ds", "e", "4")
        assertEquals(MainViewModel.DaysMustBeNumerical, viewModel.error.value)
    }

    @Test
    fun showErrorIfPeriodIsNotAnInt() {
        viewModel.addNewArea("ds", "4", "s")
        assertEquals(MainViewModel.PeriodMustBeNumerical, viewModel.error.value)
    }

    @Test
    fun showErrorIfAreaNameIsEmpty() {
        viewModel.addNewArea("", "4", "5")
        assertEquals(MainViewModel.AreaNameMustNotBeEmpty, viewModel.error.value)
    }

    @Test
    fun showErrorIfAreaNameIsSpace() {
        viewModel.addNewArea(" ", "4", "5")
        assertEquals(MainViewModel.AreaNameMustNotBeEmpty, viewModel.error.value)
    }

    @Test
    fun addNewArea() {
        val areaName = "fsfd"
        viewModel.addNewArea(areaName, "4", "5")
        verify(repositoryMock, times(1)).addArea(areaName, 4, 5)
    }

    @Test
    fun showErrorOnAddingError() {
        val areaName = "kjdf"
        Mockito.doThrow(NullPointerException::class.java).`when`(repositoryMock).addArea(areaName, 4, 5)
        viewModel.addNewArea(areaName, "4", "5")
        verify(repositoryMock, times(1)).addArea(areaName, 4, 5)
        assertEquals(MainViewModel.SomethingWentWrong, viewModel.error.value)
    }

    @Test
    fun showErrorOnSameNameError() {
        val areaName = "kjdf"
        Mockito.doThrow(SQLiteConstraintException::class.java).`when`(repositoryMock).addArea(areaName, 4, 5)
        viewModel.addNewArea(areaName, "4", "5")
        verify(repositoryMock, times(1)).addArea(areaName, 4, 5)
        assertEquals(MainViewModel.SameNameError, viewModel.error.value)
    }

    @Test
    fun loadAreasOnceAdded() {
        viewModel.addNewArea("sd", "4", "5")
        assertEquals(areas, viewModel.areas.value)
    }

    @Test
    fun fetchAreas(){
        viewModel.fetchAreas()
        assertEquals(areas, viewModel.areas.value)
    }

}