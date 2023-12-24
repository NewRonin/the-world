package com.example.theworld.data.model

data class NewsResponse(
    val status: String,
    val totalResults: Long,
    val results: List<NewsItem>,
    val nextPage: String,
)

data class NewsItem (
    val title : String,
    val link : String,
    val image_url : String,
)