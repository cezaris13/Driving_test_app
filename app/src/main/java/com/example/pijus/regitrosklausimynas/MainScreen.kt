package com.example.pijus.regitrosklausimynas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pijus.regitrosklausimynas.data.SectionData
import com.example.pijus.regitrosklausimynas.screens.DropdownMenu
import com.example.pijus.regitrosklausimynas.screens.ExpandableList
import com.example.pijus.regitrosklausimynas.theme.MyApplicationTheme

@Preview(showBackground = true, backgroundColor = 0xFFFFFF, group = "UI preview")
@Composable
fun mainScreen() {
    val coffeeDrinks = arrayOf("Americano", "Cappuccino", "Espresso", "Latte", "Mocha")
    val list = listOf(
        SectionData(
            "Section 1",
            listOf(
                "Section 1 item 1",
                "Section 1 item 2"
            )
        ),
        SectionData(
            "Section 2",
            listOf(
                "Section 2 item 1",
                "Section 2 item 2"
            )
        )
    )

    MyApplicationTheme {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            DropdownMenu(coffeeDrinks)
            ExpandableList(sections = list)
        }
    }
}

