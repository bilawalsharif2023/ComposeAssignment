package com.example.composetask.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medicines")
data class Medicine(
    @PrimaryKey val name: String,
    val dose: String,
    val strength: String
)

data class MedicineResponse(
    val medicines: List<Medicine>
)

