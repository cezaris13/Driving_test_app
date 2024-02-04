package com.example.pijus.regitrosklausimynas.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.tooling.preview.Preview
import com.example.pijus.regitrosklausimynas.data.SectionData


@Composable
fun ExpandableList(sections: List<SectionData>) {
    val isExpandedMap = List(sections.size) { _: Int -> false }.toMutableStateList()
    val isSelected = MutableList(sections.size) { mutableListOf<Boolean>() }
    sections.indices.forEach { index ->
        isSelected[index] = List(sections[index].items.size) { false }.toMutableStateList()
    }

    LazyColumn {
        sections.onEachIndexed { index, sectionData ->
            val isExpanded = isExpandedMap[index]
            val onHeaderClick = {
                isExpandedMap[index] = !isExpandedMap[index]
            }
            item {
                sectionHeader(
                    text = sectionData.headerText,
                    isExpanded = isExpanded,
                    onHeaderClicked = onHeaderClick
                )
            }

            if (isExpanded) {
                itemsIndexed(sectionData.items) { sectionIndex, item ->
                    sectionItem(item, isSelected[index][sectionIndex])// does not save checkboxes, fix later
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
fun expandablePreview() {
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
    ExpandableList(list)
}