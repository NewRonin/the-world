package com.example.theworld.data.model

import java.io.Serializable

data class NewsResponse(
    val status: String,
    val totalResults: Long,
    val results: List<NewsItem>,
    val nextPage: String,
) : Serializable

data class NewsItem (
    val title : String,
    val link : String,
    val image_url : String,
) : Serializable