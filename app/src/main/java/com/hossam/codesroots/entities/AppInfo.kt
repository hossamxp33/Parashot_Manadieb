package com.hossam.codesroots.entities


data class AppInfo(
    val `data`: List<Data>
)

data class Data(
    val about_us: String,
    val id: Int,
    val terms: String,
    val uses: String
)