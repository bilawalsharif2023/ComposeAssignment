package com.example.composetask.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composetask.models.Medicine

@Composable
fun DetailScreen(medicine: Medicine) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Name: ${medicine.name}")
        Text(text = "Dose: ${medicine.dose}")
        Text(text = "Strength: ${medicine.strength}")
    }
}