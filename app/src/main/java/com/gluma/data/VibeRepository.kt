package com.gluma.data

import com.gluma.R
import io.github.jan.supabase.postgrest.postgrest

object VibeRepository {

    val categories = listOf(
        Category("Nature", "🌲"),
        Category("Space", "🌌"),
        Category("Anime", "🌸"),
        Category("Games", "🎮"),
        Category("Summer", "☀"),
        Category("CyberPunk", "⚡")
    )

    // Local-only data: which vibe ids exist, which category they belong to,
    // and which local raw resources they use for video/audio.
    private data class LocalVibeAssets(
        val id: String,
        val category: String,
        val backgroundRes: Int,
        val trackRes: Int
    )

    private val localAssets = listOf(

        LocalVibeAssets("rainy_korea", "Nature", R.raw.rainy_korea, R.raw.still_with_you),

    )

    private var cachedVibes: List<Vibe>? = null

    private suspend fun loadAllVibes(): List<Vibe> {
        cachedVibes?.let { return it }

        val remoteInfoList = SupabaseClient.client
            .postgrest
            .from("vibes")
            .select()
            .decodeList<RemoteVibeInfo>()

        val merged = localAssets.mapNotNull { local ->
            val remote = remoteInfoList.find { it.vibe_id == local.id } ?: return@mapNotNull null
            Vibe(
                id = local.id,
                backgroundRes = local.backgroundRes,
                trackRes = local.trackRes,
                name = remote.name,
                quote = remote.quote,
                trackName = remote.track_name,
                thumbnailUrl = remote.thumbnail_url
            )
        }

        cachedVibes = merged
        return merged
    }

    suspend fun getVibes(category: String): List<Vibe> =
        loadAllVibes().filter { vibe ->
            localAssets.find { it.id == vibe.id }?.category == category
        }

    suspend fun getVibeById(vibeId: String): Vibe? =
        loadAllVibes().find { it.id == vibeId }
}