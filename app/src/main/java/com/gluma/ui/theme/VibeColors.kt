package com.gluma.ui.theme

import androidx.compose.ui.graphics.Color

data class CategoryPalette(
    val base: Color,
    val accent: Color
)

val categoryPalettes = mapOf(
    "Nature" to CategoryPalette(
        base = Color(0xFF10231C),
        accent = Color(0xFF6FBF8B)
    ),
    "Space" to CategoryPalette(
        base = Color(0xFF15122B),
        accent = Color(0xFF9C8CE8)
    ),
    "Anime" to CategoryPalette(
        base = Color(0xFF241222),
        accent = Color(0xFFE38FC0)
    ),
    "Games" to CategoryPalette(
        base = Color(0xFF13202B),
        accent = Color(0xFF5FB8D9)
    ),
    "Summer" to CategoryPalette(
        base = Color(0xFF2B1F10),
        accent = Color(0xFFE8B95F)
    ),
    "CyberPunk" to CategoryPalette(
        base = Color(0xFF1D1030),
        accent = Color(0xFFB185F0)
    )
)

val defaultPalette = CategoryPalette(
    base = Color(0xFF101014),
    accent = Color(0xFFCFCFCF)
)

fun paletteFor(categoryName: String): CategoryPalette =
    categoryPalettes[categoryName] ?: defaultPalette