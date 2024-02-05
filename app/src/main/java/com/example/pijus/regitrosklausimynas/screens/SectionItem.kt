package com.example.pijus.regitrosklausimynas.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.pijus.regitrosklausimynas.theme.MyApplicationTheme

@Composable
fun sectionItem(text: String, isChecked: MutableState<Boolean>) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Checkbox(
            checked = isChecked.value,
            onCheckedChange = { isChecked.value = it }
        )
        Text(text = text)
    }
}


@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun sectionItemPreview() {
    val text = "hello world"
    val isChecked = remember { mutableStateOf(false) }
    MyApplicationTheme {
        sectionItem(text, isChecked)
    }
}