package com.example.model

data class GlossaryTerm(
    val id: String,
    val term: String,
    val definition: String,
    val category: String,
    val example: String? = null
)
