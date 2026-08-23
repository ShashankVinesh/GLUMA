package com.gluma.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.gluma.data.FavoritesRepository
import com.gluma.data.Vibe
import com.gluma.data.VibeRepository
import com.gluma.ui.theme.paletteFor
import kotlinx.coroutines.launch

@Composable
fun SelectionScreen(categoryName: String, onVibeSelected: (String) -> Unit) {
    val vibes = remember { VibeRepository.getVibes(categoryName) }
    val palette = remember(categoryName) { paletteFor(categoryName) }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val favoriteIds by FavoritesRepository.getFavoriteIds(context).collectAsState(initial = emptySet())

    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    val columns = if (isLandscape) 2 else 1

    val favoriteVibes = vibes.filter { it.id in favoriteIds }
    val otherVibes = vibes.filterNot { it.id in favoriteIds }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF101014)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {

            Text(
                text = categoryName,
                style = MaterialTheme.typography.headlineLarge,
                color = palette.accent
            )

            Spacer(modifier = Modifier.height(20.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(columns),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (favoriteVibes.isNotEmpty()) {
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        Text(
                            text = "★ Favorites",
                            color = palette.accent,
                            style = MaterialTheme.typography.titleSmall
                        )
                    }

                    items(favoriteVibes) { vibe ->
                        VibeCard(
                            vibe = vibe,
                            isFavorite = true,
                            accent = palette.accent,
                            onClick = { onVibeSelected(vibe.id) },
                            onToggleFavorite = {
                                scope.launch { FavoritesRepository.toggleFavorite(context, vibe.id) }
                            }
                        )
                    }

                    item(span = { GridItemSpan(maxLineSpan) }) {
                        Text(
                            text = "All Vibes",
                            color = Color(0xFFB8B8B8),
                            style = MaterialTheme.typography.titleSmall,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }

                items(otherVibes) { vibe ->
                    VibeCard(
                        vibe = vibe,
                        isFavorite = false,
                        accent = palette.accent,
                        onClick = { onVibeSelected(vibe.id) },
                        onToggleFavorite = {
                            scope.launch { FavoritesRepository.toggleFavorite(context, vibe.id) }
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun VibeCard(
    vibe: Vibe,
    isFavorite: Boolean,
    accent: Color,
    onClick: () -> Unit,
    onToggleFavorite: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16f / 9f)
            .clickable { onClick() }
    ) {
        Image(
            painter = painterResource(id = vibe.thumbnailRes),
            contentDescription = vibe.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.8f)),
                        startY = 100f
                    )
                )
        )

        Text(
            text = vibe.name,
            color = Color(0xFFF5F0E8),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(12.dp)
        )

        Text(
            text = if (isFavorite) "★" else "☆",
            color = if (isFavorite) accent else Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(10.dp)
                .clickable { onToggleFavorite() }
        )
    }
}