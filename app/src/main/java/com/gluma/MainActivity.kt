package com.gluma

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gluma.ui.theme.GLUMATheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme{
                GlumaApp()
            }
        }
    }

    data class GlumaCategory(
        val name: String,
        val emoji: String
    )

    @Composable
    fun GlumaApp() {
        val categories = listOf(
            GlumaCategory("Nature", "🌲"),
            GlumaCategory("Space", "🌌"),
            GlumaCategory("Anime", "🌸"),
            GlumaCategory("Games", "🎮"),
            GlumaCategory("Summer", "☀\uFE0F"),
            GlumaCategory("CyberPunk", "⚡")
        )

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFF101014)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp, vertical = 32.dp)
            ) {

                Text(
                    text = "Vibe",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Text(
                    text = "What atmosphere do you need?",
                    modifier = Modifier.padding(top = 8.dp, bottom = 24.dp),
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.LightGray
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(14.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    items(categories) { category ->

                        CategoryCard(
                            category = category,
                            onClick = {
                                println("Selected: ${category.name}")
                            }
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun CategoryCard(
        category: GlumaCategory,
        onClick: () -> Unit
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }
                .background(
                    color = Color(0xFF1B1B22)
                )
                .padding(vertical = 40.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = category.emoji,
                    style = MaterialTheme.typography.displaySmall
                )

                Text(
                    text = category.name,
                    modifier = Modifier.padding(top = 10.dp),
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}