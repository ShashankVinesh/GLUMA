package com.gluma.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val GlumaTypography = Typography(
    // Big screen titles — "GLUMA", category names
    headlineLarge = TextStyle(
        fontFamily = Manrope,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp
    ),
    // Section labels — "What atmosphere do you need?", category name on Selection
    titleMedium = TextStyle(
        fontFamily = Manrope,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    // Small labels — "★ Favorites", "All Vibes"
    titleSmall = TextStyle(
        fontFamily = Manrope,
        fontWeight = FontWeight.SemiBold,
        fontSize = 13.sp
    ),
    // Body / vibe names on cards, track names
    bodyLarge = TextStyle(
        fontFamily = Manrope,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp
    ),
    // The atmosphere quote — the star of your reference image
    displaySmall = TextStyle(
        fontFamily = PlayfairDisplay,
        fontStyle = FontStyle.Italic,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        lineHeight = 36.sp
    )
)