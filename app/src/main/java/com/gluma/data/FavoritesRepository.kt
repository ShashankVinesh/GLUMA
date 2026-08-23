package com.gluma.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "gluma_favorites")

object FavoritesRepository {
    private val FAVORITES_KEY = stringSetPreferencesKey("favorite_vibe_ids")

    fun getFavoriteIds(context: Context): Flow<Set<String>> =
        context.dataStore.data.map { prefs -> prefs[FAVORITES_KEY] ?: emptySet() }

    suspend fun toggleFavorite(context: Context, vibeId: String) {
        context.dataStore.edit { prefs ->
            val current = prefs[FAVORITES_KEY] ?: emptySet()
            prefs[FAVORITES_KEY] = if (vibeId in current) current - vibeId else current + vibeId
        }
    }
}