package com.gluma.data

import com.gluma.R

object VibeRepository {

    val categories = listOf(
        Category("Nature", "🌲"),
        Category("Space", "🌌"),
        Category("Anime", "🌸"),
        Category("Games", "🎮"),
        Category("Summer", "☀"),
        Category("CyberPunk", "⚡")
    )

    val vibesByCategory = mapOf(

        "Nature" to listOf(
            Vibe(
                id = "rainy_korea",
                name = "Rainy Korea",
                backgroundRes = R.raw.rainy_korea,
                trackRes = R.raw.still_with_you,
                thumbnailRes = R.raw.rainy_korea_thumbnail,
                quote = "Everything is gonna be alright.",
                trackName = "Still With You"
            )
        )
    )

    fun getVibes(category: String): List<Vibe> = vibesByCategory[category].orEmpty()

    fun getVibeById(vibeId: String): Vibe? =
        vibesByCategory.values.flatten().find { it.id == vibeId }
}