package com.gluma.screens

import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import coil.compose.AsyncImage
import com.gluma.data.FavoritesRepository
import com.gluma.data.Vibe
import com.gluma.data.VibeRepository
import kotlinx.coroutines.launch

private val AccentColor = Color(0xFFE8C97A)

@Composable
fun SelectionScreen(categoryName: String, onVibeSelected: (String) -> Unit) {
    val vibes = remember { VibeRepository.getVibes(categoryName) }
    val category = remember(categoryName) {
        VibeRepository.categories.firstOrNull { it.name == categoryName }
    }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val favoriteIds by FavoritesRepository.getFavoriteIds(context).collectAsState(initial = emptySet())

    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    val columns = if (isLandscape) 2 else 1

    val favoriteVibes = vibes.filter { it.id in favoriteIds }
    val otherVibes = vibes.filterNot { it.id in favoriteIds }

    Box(modifier = Modifier.fillMaxSize()) {

        if (category != null) {
            Image(
                painter = painterResource(id = category.backgroundRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.55f),
                            Color.Black.copy(alpha = 0.85f)
                        )
                    )
                )
        )

        Column(modifier = Modifier.padding(20.dp)) {

            Text(
                text = categoryName.uppercase(),
                fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 34.sp,
                letterSpacing = 8.sp,
                color = Color(0xFFF5F0E8)
            )

            Text(
                text = "Explore the atmosphere within.",
                modifier = Modifier.padding(top = 6.dp),
                style = MaterialTheme.typography.titleMedium,
                color = AccentColor
            )

            Box(
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 20.dp)
                    .width(32.dp)
                    .height(2.dp)
                    .background(AccentColor)
            )

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
                            color = AccentColor,
                            style = MaterialTheme.typography.titleSmall
                        )
                    }

                    items(favoriteVibes) { vibe ->
                        VibeCard(
                            vibe = vibe,
                            isFavorite = true,
                            accent = AccentColor,
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
                        accent = AccentColor,
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
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val cardScale by animateFloatAsState(
        targetValue = if (isPressed) 0.97f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "cardScale"
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16f / 9f)
            .graphicsLayer {
                scaleX = cardScale
                scaleY = cardScale
            }
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
    ) {
        AsyncImage(
            model = vibe.thumbnailUrl,
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

        FavoriteStar(
            isFavorite = isFavorite,
            accent = accent,
            onToggle = onToggleFavorite,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(10.dp)
        )
    }
}

@Composable
private fun FavoriteStar(
    isFavorite: Boolean,
    accent: Color,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    var bounce by remember { mutableStateOf(false) }
    val starScale by animateFloatAsState(
        targetValue = if (bounce) 1.35f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow),
        finishedListener = { bounce = false },
        label = "starScale"
    )
    val starColor by animateColorAsState(
        targetValue = if (isFavorite) accent else Color.White,
        animationSpec = tween(250),
        label = "starColor"
    )

    Text(
        text = if (isFavorite) "★" else "☆",
        color = starColor,
        fontWeight = FontWeight.Bold,
        modifier = modifier
            .graphicsLayer {
                scaleX = starScale
                scaleY = starScale
            }
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                bounce = true
                onToggle()
            }
    )
}