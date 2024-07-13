package com.example.composetask.repository

import android.content.Context
import com.example.composetask.models.Medicine
import com.example.composetask.remote.APIServices
import com.example.composetask.room.MedicineDao
import com.example.composetask.utility.NetworkUtil
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MedicineRepository @Inject constructor(
    private val apiServices: APIServices,
    private val medicineDao: MedicineDao,
    @ApplicationContext private val context: Context
) {

    fun getMedicines(): Flow<List<Medicine>> = flow {
        if (NetworkUtil.isNetworkAvailable(context)) {
            val response = apiServices.getMedicines()
            if (response.isSuccessful && response.body() != null) {
                val medicineList = response.body()!!.medicines
                medicineDao.insertAll(medicineList)
                emit(medicineList)
            }
        } else {
            medicineDao.getAllMedicines().collect { medicines ->
                emit(medicines)
            }
        }
    }
}
