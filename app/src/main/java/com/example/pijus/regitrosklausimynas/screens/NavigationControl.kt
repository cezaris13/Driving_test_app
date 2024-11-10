package com.example.pijus.regitrosklausimynas.screens


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pijus.regitrosklausimynas.data.MistakeTypeGroup

@Composable
fun NavigationControl(inputs: ArrayList<MistakeTypeGroup>?) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "mainScreen") {
        composable("mainScreen") {
            if (inputs != null) {
                mainScreen(inputs)
            } else {
                Text(text = "Failed to retrieve data")
            }
        }
    }
}