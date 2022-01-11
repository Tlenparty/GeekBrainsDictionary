package com.geekbrains.geekbrainsdictionary.model.data

import com.google.gson.annotations.SerializedName

class Translation(
    @SerializedName("text")
    val translation: String?
)