package com.geekbrains.geekbrainsdictionary.model.datasource.remote

import com.geekbrains.geekbrainsdictionary.model.data.DataModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("words/search")
    suspend fun search(@Query("search") word:String): List<DataModel>
}