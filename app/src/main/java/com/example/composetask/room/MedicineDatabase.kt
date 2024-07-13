package com.example.composetask.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.composetask.models.Medicine

@Database(entities = [Medicine::class], version = 1, exportSchema = false)
abstract class MedicineDatabase : RoomDatabase() {
    abstract fun medicineDao(): MedicineDao
}
