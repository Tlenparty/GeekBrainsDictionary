package com.geekbrains.geekbrainsdictionary.data.remote

import com.geekbrains.geekbrainsdictionary.model.data.DataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("words/search")
    suspend fun search(@Query("search") word:String): List<DataModel>
}