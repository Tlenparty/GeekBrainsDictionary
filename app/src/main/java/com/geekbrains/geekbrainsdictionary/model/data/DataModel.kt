package com.geekbrains.geekbrainsdictionary.model.data

import com.google.gson.annotations.SerializedName

// Модель данных
data class DataModel(
    // Слово словаря
    @SerializedName("text")
    val text:String?,
    // Список значений слова
    @SerializedName("meanings")
    val meaning:List<Meanings>?
)
