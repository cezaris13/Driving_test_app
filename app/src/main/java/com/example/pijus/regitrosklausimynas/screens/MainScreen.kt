package com.example.pijus.regitrosklausimynas.screens

import android.os.Environment
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Save
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.itextpdf.text.Document
import com.itextpdf.text.Paragraph
import com.itextpdf.text.pdf.PdfWriter
import java.io.FileOutputStream
import java.text.Normalizer
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.regex.Pattern

@Composable
fun mainScreen(mistakes: ArrayList<MistakeTypeGroup>) {
    val context = LocalContext.current
    val mistakeGroupNames = mistakes.map { it.name }
    var showAlertDialog by remember { mutableStateOf(true) }
    var currentSelected by remember { mutableStateOf(0) }
    val isSelected = mistakes.map { mistakeTypeGroup ->
        mistakeTypeGroup.groups.map {
            MutableList(it.items.size) { remember { mutableStateOf(false) } }
        }.toMutableList()
    }

    fun deAccent(str: String?): String {
        val nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD)
        val pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+")
        return pattern.matcher(nfdNormalizedString).replaceAll("")
    }

    fun savePdf() {
        val mDoc = Document()
        val fileName =
            SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(System.currentTimeMillis())
        val filePath =
            Environment.getExternalStorageDirectory().toString() + "/" + fileName + ".pdf"
        try {
            PdfWriter.getInstance(mDoc, FileOutputStream(filePath))
            mDoc.open()
            var textToFile = ""
            for (i in mistakes.indices) {
                for (j in mistakes[i].groups.indices) {
                    for (k in mistakes[i].groups[j].items.indices) {
                        if (isSelected[i][j][k].value) {
                            textToFile += mistakes[i].groups[j].items[k]
                            textToFile += "\n"
                        }
                    }
                }
            }
            mDoc.add(Paragraph(deAccent(textToFile)))
            mDoc.close()
            Toast.makeText(
                context,
                "Saved succesfully$fileName.pdf \n saved to $filePath",
                Toast.LENGTH_SHORT
            ).show()
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    MyApplicationTheme {
        if (showAlertDialog) {
            AlertDialog(
                icon = {
                    Icon(
                        Icons.Rounded.Save,
                        "saveIcon"
                    )
                },
                title = { Text("Klaidų išsaugojimas") },
                text = { Text("Ar jūs tikrai norite išsaugoti šiuos duomenis?") },
                onDismissRequest = { showAlertDialog = false },
                confirmButton = {
                    TextButton(onClick = {
//                        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M && checkSelfPermission(
//                                Manifest.permission.WRITE_EXTERNAL_STORAGE
//                            ) == PackageManager.PERMISSION_DENIED
//                        ) {
//                            val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                            requestPermissions(permissions, 1000)
//                        } else {
                        savePdf()
//                        }
                    }) {
                        Text("Taip")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showAlertDialog = false }) {
                        Text("Ne")
                    }
                }
            )
        }

        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            val onClickAction = { item: String ->
                currentSelected = mistakeGroupNames.indexOf(item)
            }
            DropdownMenu(mistakeGroupNames, onClickAction)
            ExpandableList(
                mistakeTypeGroup = mistakes[currentSelected],
                isSelected[currentSelected]
            )
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { showAlertDialog = true },
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
