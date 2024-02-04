package com.example.pijus.regitrosklausimynas

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.pijus.regitrosklausimynas.ExpandableListDataPump.Companion.getMistakes
import com.example.pijus.regitrosklausimynas.data.MistakeTypeGroup
import com.example.pijus.regitrosklausimynas.data.OneMistake
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

class MainActivity : AppCompatActivity() {
    var position = 0
    private var submitButton: Button? = null
    var expandableListView: ExpandableListView? = null
    var expandableListAdapter = arrayOfNulls<ExpandableListAdapter>(3)
    private var expandableListTitle = ArrayList<List<String>>()
    private var expandableListDetail = ArrayList<LinkedHashMap<String, List<OneMistake>>>()

    private suspend fun retrieveData(): ArrayList<MistakeTypeGroup>? {
        val database = Firebase.database
        val myRef = database.getReference("mistakes")
        val dataSnapshot: DataSnapshot = myRef.get().await()
        val t: GenericTypeIndicator<ArrayList<MistakeTypeGroup>?> =
            object : GenericTypeIndicator<ArrayList<MistakeTypeGroup>?>() {}
        return dataSnapshot.getValue(t)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycleScope.launch {
            var response = retrieveData()
        }
//        print(response.toString())
        expandableListView = findViewById<View>(R.id.Expandable) as ExpandableListView
        for (i in 0..2) {
            expandableListDetail.add(LinkedHashMap(getMistakes(i)))
            expandableListTitle.add(ArrayList(expandableListDetail[i].keys))
            expandableListAdapter[i] =
                CustomExpandableListAdapter(this, expandableListTitle[i], expandableListDetail[i])
        }
        expandableListView!!.setAdapter(expandableListAdapter[0])
        expandableListView!!.setOnGroupExpandListener { }
        expandableListView!!.setOnGroupCollapseListener { }
        expandableListView!!.setOnChildClickListener { _, v, groupPosition, childPosition, _ ->
            for (i in 0..2) {
                if (position == i) {
                    if (!expandableListDetail[i][expandableListTitle[i][groupPosition]]!![childPosition].isSelected) {
                        expandableListDetail[i][expandableListTitle[i][groupPosition]]!![childPosition].isSelected =
                            true
                        val selected = v.findViewById<View>(R.id.checkbox) as ImageView
                        selected.setBackgroundResource(R.drawable.checked)
                    } else {
                        expandableListDetail[i][expandableListTitle[i][groupPosition]]!![childPosition].isSelected =
                            false
                        val selected = v.findViewById<View>(R.id.checkbox) as ImageView
                        selected.setBackgroundResource(R.drawable.check)
                    }
                }
            }
            false
        }
        val dropdown = findViewById<View>(R.id.Spinerris) as Spinner
        val arrayList = ArrayList<String>()
        arrayList.add("Nekritines klaidos")
        arrayList.add("Kritines klaidos")
        arrayList.add("Bendrosios klaidos")
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayList)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        dropdown.adapter = arrayAdapter
        dropdown.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                this@MainActivity.position = position
                for (i in 0..2) {
                    if (this@MainActivity.position == i) {
                        expandableListView!!.setAdapter(expandableListAdapter[i])
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        submitButton = findViewById(R.id.Button1)
        submitButton?.setOnClickListener {
            AlertDialog.Builder(this@MainActivity)
                .setIcon(R.drawable.save1)
                .setTitle("Klaidų išsaugojimas")
                .setMessage("Ar jūs tikrai norite išsaugoti šiuos duomenis?")
                .setPositiveButton("Taip") { _, _ ->
                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                            val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            requestPermissions(permissions, STORAGE_CODE)
                        } else {
                            savePdf()
                        }
                    } else {
                        savePdf()
                    }
                }
                .setNegativeButton("Ne", null)
                .show()
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
            for (i in 0..2) {
                for (j in 0 until expandableListDetail[i].size) {
                    for (k in expandableListDetail[i][expandableListTitle[i][j]]!!.indices) {
                        if (expandableListDetail[i][expandableListTitle[i][j]]!![k].isSelected) {
                            textToFile += expandableListDetail[i][expandableListTitle[i][j]]!![k].oneMistake
                            textToFile += "\n"
                        }
                    }
                }
            }
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
}