package com.joebarker.howlongcanistay

import com.joebarker.howlongcanistay.repository.AddAreaRepository
import com.joebarker.howlongcanistay.viewModels.MainViewModel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class MainViewModelShould {
    private val repositoryMock = mock<AddAreaRepository>()
    private val viewModel = MainViewModel(repositoryMock)

    @Test
    fun showErrorIfDaysAllowedAreMoreThanPeriod() {
        viewModel.addNewArea("ds", "5", "4")
        assertEquals(viewModel.error.value, MainViewModel.PeriodCannotBeLower)
    }

    @Test
    fun showErrorIfDaysAllowedIsNotAnInt() {
        viewModel.addNewArea("ds", "e", "4")
        assertEquals(viewModel.error.value, MainViewModel.DaysMustBeNumerical)
    }

    @Test
    fun showErrorIfPeriodIsNotAnInt() {
        viewModel.addNewArea("ds", "4", "s")
        assertEquals(viewModel.error.value, MainViewModel.PeriodMustBeNumerical)
    }

    @Test
    fun showErrorIfAreaNameIsEmpty() {
        viewModel.addNewArea("", "4", "5")
        assertEquals(viewModel.error.value, MainViewModel.AreaNameMustNotBeEmpty)
    }

    @Test
    fun showErrorIfAreaNameIsSpace() {
        viewModel.addNewArea(" ", "4", "5")
        assertEquals(viewModel.error.value, MainViewModel.AreaNameMustNotBeEmpty)
    }

    @Test
    fun addNewArea() {
        val areaName = "kjdf"
        viewModel.addNewArea(areaName, "4", "5")
        verify(repositoryMock, times(1)).addArea(areaName, 4, 5)
    }

    @Test
    fun showErrorOnAddingError() {
        val areaName = "kjdf"
        Mockito.doThrow(NullPointerException::class.java).`when`(repositoryMock).addArea(areaName, 4, 5)
        viewModel.addNewArea(areaName, "4", "5")
        verify(repositoryMock, times(1)).addArea(areaName, 4, 5)
        assertEquals(viewModel.error.value, MainViewModel.SomethingWentWrong)
    }

}