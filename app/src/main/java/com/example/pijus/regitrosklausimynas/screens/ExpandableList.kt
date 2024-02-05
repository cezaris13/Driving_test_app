package com.example.pijus.regitrosklausimynas.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.tooling.preview.Preview
import com.example.pijus.regitrosklausimynas.data.MistakeGroup
import com.example.pijus.regitrosklausimynas.data.MistakeTypeGroup


@Composable
fun ExpandableList(mistakeTypeGroup: MistakeTypeGroup) {
    val isExpandedMap = List(mistakeTypeGroup.groups.size) { _: Int -> false }.toMutableStateList()
    val isSelected = MutableList(mistakeTypeGroup.groups.size) { mutableListOf<Boolean>() }
    mistakeTypeGroup.groups.indices.forEach { index ->
        isSelected[index] =
            List(mistakeTypeGroup.groups[index].items.size) { false }.toMutableStateList()
    }

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
                    )// does not save checkboxes, fix later
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
    ExpandableList(mistakeTypeGroup)
}