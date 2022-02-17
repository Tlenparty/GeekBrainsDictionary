package com.geekbrains.geekbrainsdictionary.data.local

import com.geekbrains.geekbrainsdictionary.data.local.model.HistoryEntity
import com.geekbrains.geekbrainsdictionary.data.local.model.toEntity
import com.geekbrains.geekbrainsdictionary.model.data.AppState
import com.geekbrains.geekbrainsdictionary.model.data.DataModel
import com.geekbrains.geekbrainsdictionary.model.datasource.local.DataSourceLocal

class RoomDataSource(
    private val historyDao: HistoryDao,
) : DataSourceLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return historyDao.all().map(HistoryEntity::toDomainModel)
    }

    override suspend fun saveData(appState: AppState) {
        val entity = appState.toEntity() ?: return // чтобы обойти null
        historyDao.insert(entity)
    }
}