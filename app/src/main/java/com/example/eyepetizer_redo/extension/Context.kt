package com.example.eyepetizer_redo.extension

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.eyepetizer_redo.EyepetizerApplication

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = EyepetizerApplication.context.packageName + "_preferences",
    produceMigrations = { context ->
        listOf(SharedPreferencesMigration(context, EyepetizerApplication.context.packageName + "_preferences"))
    })