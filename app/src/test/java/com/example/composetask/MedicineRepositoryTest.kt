package com.example.composetask

import android.content.Context
import com.example.composetask.models.Medicine
import com.example.composetask.models.MedicineResponse
import com.example.composetask.remote.APIServices
import com.example.composetask.room.MedicineDao
import com.example.composetask.repository.MedicineRepository
import com.example.composetask.utility.NetworkUtil
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class MedicineRepositoryTest {

    private lateinit var repository: MedicineRepository
    private lateinit var apiServices: APIServices
    private lateinit var medicineDao: MedicineDao
    private lateinit var context: Context

    @Before
    fun setup() {
        apiServices = mockk()
        medicineDao = mockk()
        context = mockk()
        repository = MedicineRepository(apiServices, medicineDao, context)
    }

    @Test
    fun `test getMedicines when network available`() {
        // Given
        val mockResponse = Response.success(
            MedicineResponse(listOf(
                Medicine("Medicine A", "10 mg", "Strong"),
                Medicine("Medicine B", "5 mg", "Medium")
            ))
        )
        val mockMedicineList = mockResponse.body()?.medicines ?: emptyList()
        coEvery { apiServices.getMedicines() } returns mockResponse
        coEvery { NetworkUtil.isNetworkAvailable(context) } returns true
        coEvery { medicineDao.insertAll(mockMedicineList) } returns Unit
        coEvery { medicineDao.getAllMedicines() } returns flowOf(mockMedicineList)

        // When
        val result = runBlocking { repository.getMedicines().first() }

        // Then
        assertEquals(mockMedicineList, result)
    }
}
