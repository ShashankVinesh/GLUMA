package com.gluma.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gluma.data.VibeRepository

@Composable
fun AtmosphereScreen(vibeId: String, onBack: () -> Unit) {
    val vibe = VibeRepository.getVibeById(vibeId)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF101014)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

            Text(
                text = "←",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(20.dp)
                    .clickable { onBack() }
            )

            Text(
                text = "Still With You",
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(20.dp)
            )

            Text(
                text = "Everything is gonna be alright.",
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(20.dp)
            )

            Text(
                text = vibe?.name ?: "Unknown Vibe",
                color = Color.LightGray,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }
}

