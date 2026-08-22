package com.gluma.data

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
        "Anime" to listOf(
            Vibe("frieren", "Frieren", "https://example.com/frieren.jpg"),
            Vibe("cowboy_bebop", "Cowboy Bebop", "https://example.com/bebop.jpg")
        ),
        "Nature" to listOf(
            Vibe("rainy_korea", "Rainy Korea",  "https://example.com/rainy_korea.jpg"),
            Vibe("misty_forest", "Misty Forest", "https://example.com/forest.jpg")
        ),
        "Space" to listOf(
            Vibe("nebula", "Nebula Drift",  "https://example.com/nebula.jpg")
        )

    )
    fun getVibes(category: String): List<Vibe> = vibesByCategory[category].orEmpty()

    fun getVibeById(vibeId: String): Vibe? =
        vibesByCategory.values.flatten().find { it.id == vibeId }


}