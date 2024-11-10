package com.example.pijus.regitrosklausimynas.data

data class MistakeTypeGroup(
    var name: String = "",
    var groups: ArrayList<MistakeGroup> = arrayListOf()
)