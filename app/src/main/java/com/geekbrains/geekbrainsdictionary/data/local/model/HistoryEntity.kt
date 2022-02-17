package com.geekbrains.geekbrainsdictionary.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.geekbrains.geekbrainsdictionary.model.data.AppState
import com.geekbrains.geekbrainsdictionary.model.data.DataModel

//Индексация бд по основнмоу слову. По слову будем искать, unique- если мы не хотим сохранять снова и снова
@Entity(
    indices = [Index(value = ["word"], unique = true)]
)
data class HistoryEntity(
    @PrimaryKey
    @ColumnInfo(name = "word")
    val word: String,

    @ColumnInfo(name = "description")
    val description: String? = null
) {
    fun toDomainModel() = DataModel(
        text = word,
        null
    )
}

fun AppState.toEntity() = when (this) {
    is AppState.Success -> {
        val searchResult = this.data
        if (searchResult.isEmpty()) {
            null
        } else {
            HistoryEntity(searchResult.first().text.orEmpty(), null)
        }
    }
    else -> null
}
