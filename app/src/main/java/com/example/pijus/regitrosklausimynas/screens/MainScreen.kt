package com.example.pijus.regitrosklausimynas.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pijus.regitrosklausimynas.data.MistakeGroup
import com.example.pijus.regitrosklausimynas.data.MistakeTypeGroup
import com.example.pijus.regitrosklausimynas.theme.MyApplicationTheme

@Composable
fun mainScreen(mistakes: ArrayList<MistakeTypeGroup>) {
    val context = LocalContext.current
    val mistakeGroupNames = mistakes.map { it.name }
    var currentSelected by remember { mutableStateOf(0) }

    MyApplicationTheme {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            val onClickAction = { item: String ->
                Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                currentSelected = mistakeGroupNames.indexOf(item)
            }
            DropdownMenu(mistakeGroupNames, onClickAction)
            ExpandableList(mistakeTypeGroup = mistakes[currentSelected])
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    //            AlertDialog.Builder(this@MainActivity)
//                .setIcon(R.drawable.save1)
//                .setTitle("Klaidų išsaugojimas")
//                .setMessage("Ar jūs tikrai norite išsaugoti šiuos duomenis?")
//                .setPositiveButton("Taip") { _, _ ->
//                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
//                        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
//                            val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                            requestPermissions(permissions, STORAGE_CODE)
//                        } else {
//                            savePdf()
//                        }
//                    } else {
//                        savePdf()
//                    }
//                }
//                .setNegativeButton("Ne", null)
//                .show()
//        }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Išsaugoti")
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF, group = "UI preview")
@Composable
fun mainScreenPreview() {
    val mistake = arrayListOf(
        MistakeTypeGroup(
            "Kritinės klaidos", arrayListOf(
                MistakeGroup(
                    "Parkavimas", arrayListOf(
                        "first mistake",
                        "second mistake"
                    )
                )
            )
        ),
        MistakeTypeGroup(
            "Nekritinės klaidos", arrayListOf(
                MistakeGroup(
                    "Parkavimas", arrayListOf(
                        "first mistake",
                        "second mistake"
                    )
                )
            )
        )
    )
    mainScreen(mistake)
}
