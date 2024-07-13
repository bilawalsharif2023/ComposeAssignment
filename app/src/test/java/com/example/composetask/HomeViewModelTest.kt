package com.example.composetask

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.example.composetask.models.Medicine
import com.example.composetask.repository.MedicineRepository
import com.example.composetask.viewmodels.HomeViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: HomeViewModel
    private lateinit var repository: MedicineRepository
    private lateinit var savedStateHandle: SavedStateHandle

    @Before
    fun setup() {
        repository = mockk()
        savedStateHandle = mockk()
        viewModel = HomeViewModel(repository, savedStateHandle)
    }

    @Test
    fun `test getMedicines when network available`() {
        // Given
        val mockMedicineList = listOf(
            Medicine("Medicine A", "10 mg", "Strong"),
            Medicine("Medicine B", "5 mg", "Medium")
        )
        coEvery { runBlocking { repository.getMedicines() } } returns flowOf(mockMedicineList)

        // When
        viewModel.medicines

        // Then
        assertEquals(mockMedicineList, viewModel.medicines.value)
    }

    // Add more tests as needed for different scenarios
}
