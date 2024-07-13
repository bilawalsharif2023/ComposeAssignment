@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.composetask.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composetask.models.Medicine
import com.example.composetask.viewmodels.HomeViewModel
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@SuppressLint("SuspiciousIndentation")
@Composable
fun HomeScreen(onClick: (medicine: Medicine) -> Unit) {
    val homeViewModel: HomeViewModel = hiltViewModel()

    val medicines by homeViewModel.medicines.collectAsState()
    val isLoading by homeViewModel.isLoading.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        Box(
            modifier = Modifier
                .padding(8.dp)
                .border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(4.dp)
                )
        ) {
            Text(
                text = "Hi, ${homeViewModel.savedStateHandle.get<String>("email")} ${CurrentTime()}",
                modifier = Modifier.padding(4.dp)
            )
        }

        if (isLoading) {
            LoadingDialog()
        } else {
            LazyVerticalGrid(columns = GridCells.Fixed(1)) {
                items(medicines) { medicine ->
                    MyCard(medicine, onClick)
                }
            }
        }
    }
}

@Composable
fun MyCard(medicine: Medicine, onClick: (medicine: Medicine) -> Unit) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .height(150.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, Color(0xFFEEEEEE))
            .shadow(4.dp),
        onClick = { onClick(medicine) }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Name: ${medicine.name}")
            Text(text = "Dose: ${medicine.dose}")
            Text(text = "Strength: ${medicine.strength}")
        }
    }
}

@Composable
fun CurrentTime(): String {
    var currentTime by remember { mutableStateOf(getCurrentTime()) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000) // Update time every second
            currentTime = getCurrentTime()
        }
    }

    return currentTime
}

private fun getCurrentTime(): String {
    val sdf = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    return sdf.format(Date())
}

@Composable
fun LoadingDialog() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White.copy(alpha = 0.7f))
            .wrapContentSize(Alignment.Center)
    ) {
        Text("Please wait...", color = Color.Black) // Text view for "Please wait..."
    }
}
