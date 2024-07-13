package com.example.composetask

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.example.composetask.models.Medicine
import com.example.composetask.repository.MedicineRepository
import com.example.composetask.viewmodels.HomeViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: HomeViewModel
    private lateinit var repository: MedicineRepository
    private lateinit var savedStateHandle: SavedStateHandle
    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk()
        savedStateHandle = mockk()
        viewModel = HomeViewModel(repository,savedStateHandle)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testScope.cleanupTestCoroutines()
    }

    @Test
    fun `test getMedicines when network available`() = testScope.runBlockingTest {
        // Given
        val mockMedicineList = listOf(
            Medicine("Medicine A", "10 mg", "Strong"),
            Medicine("Medicine B", "5 mg", "Medium")
        )
        coEvery { repository.getMedicines() } returns flowOf(mockMedicineList)

        // When
        println("Calling getMedicines")
        viewModel.medicines

        // Then
        try {
            val result = viewModel.medicines.first()
            println("Result from getMedicines: $result")
            assertEquals(mockMedicineList, result)
        } catch (e: Throwable) {
            println("Error in test: ${e.message}")
            throw e // rethrow the exception for better visibility in logs
        }
    }

}
