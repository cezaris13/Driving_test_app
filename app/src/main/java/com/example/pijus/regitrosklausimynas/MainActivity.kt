package com.example.pijus.regitrosklausimynas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.example.pijus.regitrosklausimynas.data.MistakeTypeGroup
import com.example.pijus.regitrosklausimynas.screens.NavigationControl
import com.example.pijus.regitrosklausimynas.theme.MyApplicationTheme
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mistakes = MutableLiveData<ArrayList<MistakeTypeGroup>?>()

        lifecycleScope.launch {
            mistakes.postValue(retrieveData())
        }

        setContent {
            val inputs: ArrayList<MistakeTypeGroup>? by mistakes.observeAsState()

            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationControl(inputs)
                }
            }
        }
    }

    private suspend fun retrieveData(): ArrayList<MistakeTypeGroup>? {
        val database = Firebase.database
        val myRef = database.getReference("mistakes")
        val dataSnapshot: DataSnapshot = myRef.get().await()
        val t: GenericTypeIndicator<ArrayList<MistakeTypeGroup>?> =
            object : GenericTypeIndicator<ArrayList<MistakeTypeGroup>?>() {}
        return dataSnapshot.getValue(t)
    }
}