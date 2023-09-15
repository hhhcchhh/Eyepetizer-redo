package com.example.eyepetizer_redo.util

import androidx.datastore.core.DataStore
import com.example.eyepetizer_redo.EyepetizerApplication
import kotlinx.coroutines.runBlocking
import java.util.prefs.Preferences

object DataStoreUtils {
    /**
     * 获取DataStore实例。
     */
    val dataStore: DataStore<Preferences> = EyepetizerApplication.context.dataStore

    fun readBooleanData(key: String, default: Boolean = false): Boolean {
        var value = false
        runBlocking {
            dataStore.data.first {
                value = it[booleanPreferencesKey(key)] ?: default
                true
            }
        }
        return value
    }
}