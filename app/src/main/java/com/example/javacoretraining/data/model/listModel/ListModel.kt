package com.example.javacoretraining.data.model.listModel

data class ListModel(
    val `data`: List<Data>,
    val page: Int,
    val per_page: Int,
    val support: Support,
    val total: Int,
    val total_pages: Int
)