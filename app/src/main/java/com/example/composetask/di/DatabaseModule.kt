package com.example.composetask.di

import android.content.Context
import androidx.room.Room
import com.example.composetask.room.MedicineDao
import com.example.composetask.room.MedicineDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MedicineDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            MedicineDatabase::class.java,
            "medicine_database"
        ).build()
    }

    @Provides
    fun provideMedicineDao(database: MedicineDatabase): MedicineDao {
        return database.medicineDao()
    }
}
