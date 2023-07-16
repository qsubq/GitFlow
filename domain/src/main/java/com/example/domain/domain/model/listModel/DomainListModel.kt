package com.example.domain.domain.model.listModel

data class DomainListModel(
    val `data`: List<DomainData>,
    val page: Int,
    val per_page: Int,
    val support: Support,
    val total: Int,
    val total_pages: Int
)