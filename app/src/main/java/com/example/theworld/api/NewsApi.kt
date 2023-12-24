package com.example.theworld.api

import com.example.theworld.data.model.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.theworld.Constants.NewsAPIConstants as api

interface NewsApi {
    @GET(api.APInews + api.APIkey)
    fun getNews(@Query("q") keyword : String?) : Call<NewsResponse>
}