package com.geekbrains.geekbrainsdictionary.model.data

import com.google.gson.annotations.SerializedName

// Описывает занчение фразы или слова
data class Meanings(
    // Перевод
    @SerializedName("translation")
    val translation:Translation?,
    // Ссылка URL картинки
    @SerializedName("imageUrl")
    val imageUrl:String?
)
