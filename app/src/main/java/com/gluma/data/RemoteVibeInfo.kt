package com.gluma.data

import kotlinx.serialization.Serializable

@Serializable
data class RemoteVibeInfo(
    val vibe_id: String,
    val name: String,
    val quote: String,
    val track_name: String,
    val thumbnail_url: String
)