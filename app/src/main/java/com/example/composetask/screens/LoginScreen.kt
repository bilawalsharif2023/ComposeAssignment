package com.example.composetask.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composetask.R

@Composable
fun LoginScreen(onClick: (email:String) -> Unit) {
    var email by remember {
        mutableStateOf("")
    }
   var password by remember {
        mutableStateOf("")
    }

    Column(modifier=Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
) {

        Image(painter = painterResource(id = R.drawable.account), contentDescription = "image", modifier = Modifier.size(150.dp))
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Welcome Back", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Text(text = "Login to your account")
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = email, onValueChange = {email=it}, label = { Text(text = "Email Address")})
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = password, onValueChange = {password=it}, label = { Text(text = "Password")},
            visualTransformation = PasswordVisualTransformation())
        Spacer(modifier = Modifier.height(16.dp))

        Button(modifier = Modifier.width(150.dp),   colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF348fd9),
            contentColor = Color.White
        ), onClick = {
            onClick(email)
        }) {
            Text(text = "Login")

        }

    }


}