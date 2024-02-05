package com.example.pijus.regitrosklausimynas

import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
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
import com.itextpdf.text.Document
import com.itextpdf.text.Paragraph
import com.itextpdf.text.pdf.PdfWriter
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.io.FileOutputStream
import java.text.Normalizer
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.regex.Pattern

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

    private fun savePdf() {
        val mDoc = Document()
        val fileName =
            SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(System.currentTimeMillis())
        val filePath =
            Environment.getExternalStorageDirectory().toString() + "/" + fileName + ".pdf"
        try {
            PdfWriter.getInstance(mDoc, FileOutputStream(filePath))
            mDoc.open()
            var textToFile = ""
//            for (i in 0..2) {
//                for (j in 0 until expandableListDetail[i].size) {
//                    for (k in expandableListDetail[i][expandableListTitle[i][j]]!!.indices) {
//                        if (expandableListDetail[i][expandableListTitle[i][j]]!![k].isSelected) {
//                            textToFile += expandableListDetail[i][expandableListTitle[i][j]]!![k].oneMistake
//                            textToFile += "\n"
//                        }
//                    }
//                }
//            }
            mDoc.add(Paragraph(deAccent(textToFile)))
            mDoc.close()
            Toast.makeText(
                this,
                "Saved succesfully$fileName.pdf \n saved to $filePath",
                Toast.LENGTH_SHORT
            ).show()
        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            STORAGE_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "permission denied...!!!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun deAccent(str: String?): String {
        val nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD)
        val pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+")
        return pattern.matcher(nfdNormalizedString).replaceAll("")
    }

    companion object {
        private const val STORAGE_CODE = 1000
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