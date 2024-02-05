package com.example.pijus.regitrosklausimynas.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material.icons.rounded.ArrowDropUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pijus.regitrosklausimynas.theme.MyApplicationTheme

@Composable
fun sectionHeader(text: String, isExpanded: Boolean, onHeaderClicked: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onHeaderClicked() }
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .weight(1.0f)
                .padding(
                    horizontal = 15.dp
                )
        )
        if (isExpanded) {
            Icon(
                Icons.Rounded.ArrowDropUp,
                "dropUpIcon"
            )
        } else {
            Icon(
                Icons.Rounded.ArrowDropDown,
                "dropDownIcon"
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun sectionHeaderPreview() {
    val text = "header"
    var isExpanded by remember {mutableStateOf(false)}

    MyApplicationTheme {
        sectionHeader(text, isExpanded) {
            isExpanded = !isExpanded
        }
    }
}