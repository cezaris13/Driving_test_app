package com.example.pijus.regitrosklausimynas.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun sectionItem(text: String, isChecked: Boolean) {
    var isItemChecked by remember { mutableStateOf(isChecked) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Checkbox(
            checked = isItemChecked,
            onCheckedChange = { isItemChecked = it }
        )
        Text(text = text)
    }
}


@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun sectionItemPreview() {
    val text = "hello world"
    val isChecked = true
    sectionItem(text, isChecked)
}