package com.example.pijus.regitrosklausimynas.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.tooling.preview.Preview
import com.example.pijus.regitrosklausimynas.data.MistakeGroup
import com.example.pijus.regitrosklausimynas.data.MistakeTypeGroup
import com.example.pijus.regitrosklausimynas.theme.MyApplicationTheme


@Composable
fun ExpandableList(
    mistakeTypeGroup: MistakeTypeGroup,
    isSelected: MutableList<MutableList<MutableState<Boolean>>>
) {
    val isExpandedMap = List(mistakeTypeGroup.groups.size) { _: Int -> false }.toMutableStateList()

    LazyColumn {
        mistakeTypeGroup.groups.onEachIndexed { index, sectionData ->
            val isExpanded = isExpandedMap[index]
            val onHeaderClick = {
                isExpandedMap[index] = !isExpandedMap[index]
            }
            item {
                sectionHeader(
                    text = sectionData.name,
                    isExpanded = isExpanded,
                    onHeaderClicked = onHeaderClick
                )
            }

            if (isExpanded) {
                itemsIndexed(sectionData.items) { sectionIndex, item ->
                    sectionItem(
                        item,
                        isSelected[index][sectionIndex]
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
fun expandablePreview() {
    val mistakeTypeGroup = MistakeTypeGroup(
        "Nekritinės klaidos", arrayListOf(
            MistakeGroup(
                "Parkavimas", arrayListOf(
                    "first mistake",
                    "second mistake"
                )
            )
        )
    )

    val isSelected = mistakeTypeGroup.groups.map {
        MutableList(it.items.size) { remember { mutableStateOf(false) } }
    }.toMutableList()

    MyApplicationTheme {
        ExpandableList(mistakeTypeGroup, isSelected)
    }
}