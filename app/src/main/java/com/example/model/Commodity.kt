package com.example.model

data class Commodity(
    val id: String,
    val name: String,
    val latinName: String,
    val emoji: String,
    val description: String,
    val growthRequirements: List<String>,
    val propagation: String,
    val productionStages: List<String>,
    val postHarvest: List<String>,
    val funFact: String
)
