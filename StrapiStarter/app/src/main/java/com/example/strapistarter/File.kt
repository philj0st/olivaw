package com.example.strapistarter

data class File(
    val alternativeText: String,
    val caption: String,
    val createdAt: String,
    val ext: String,
    val formats: Formats,
    val hash: String,
    val height: Int,
    val id: Int,
    val mime: String,
    val name: String,
    val previewUrl: Any,
    val provider: String,
    val provider_metadata: Any,
    val size: Double,
    val updatedAt: String,
    val url: String,
    val width: Int
)