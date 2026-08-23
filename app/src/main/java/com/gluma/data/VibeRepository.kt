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
            Vibe("rainy_korea", "Rainy Korea",  R.raw.rainy_korea , "Rainy Korea" , "Still With You" , R.raw.still_with_you )
        ),


    )
    fun getVibes(category: String): List<Vibe> = vibesByCategory[category].orEmpty()

    fun getVibeById(vibeId: String): Vibe? =
        vibesByCategory.values.flatten().find { it.id == vibeId }


}