package com.gluma.screens

import android.content.pm.ActivityInfo
import android.view.ViewGroup
import androidx.annotation.OptIn
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.gluma.data.Vibe
import com.gluma.data.VibeRepository
import com.gluma.utils.findActivity

@OptIn(UnstableApi::class)
@Composable
fun AtmosphereScreen(vibeId: String, onBack: () -> Unit) {
    val vibe = remember { VibeRepository.getVibeById(vibeId) }
    val context = LocalContext.current
    val activity = context.findActivity()

    DisposableEffect(Unit) {
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        onDispose {
            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        }
    }

    DisposableEffect(Unit) {
        val window = activity?.window
        if (window != null) {
            WindowCompat.setDecorFitsSystemWindows(window, false)
            val controller = WindowInsetsControllerCompat(window, window.decorView)
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }

        onDispose {
            if (window != null) {
                WindowCompat.setDecorFitsSystemWindows(window, true)
                val controller = WindowInsetsControllerCompat(window, window.decorView)
                controller.show(WindowInsetsCompat.Type.systemBars())
            }
        }
    }

    DisposableEffect(Unit) {
        val window = activity?.window
        val decorView = window?.decorView

        val listener = android.view.ViewTreeObserver.OnWindowFocusChangeListener { hasFocus ->
            if (hasFocus && window != null) {
                WindowCompat.setDecorFitsSystemWindows(window, false)
                WindowInsetsControllerCompat(window, window.decorView)
                    .hide(WindowInsetsCompat.Type.systemBars())
            }
        }
        decorView?.viewTreeObserver?.addOnWindowFocusChangeListener(listener)

        onDispose {
            decorView?.viewTreeObserver?.removeOnWindowFocusChangeListener(listener)
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF101014)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

            if (vibe != null) {
                // Background video — muted, looping
                val videoPlayer = remember {
                    ExoPlayer.Builder(context).build().apply {
                        setMediaItem(
                            MediaItem.fromUri("android.resource://${context.packageName}/${vibe!!.backgroundRes}")
                        )
                        repeatMode = ExoPlayer.REPEAT_MODE_ONE
                        volume = 0f
                        prepare()
                        playWhenReady = true
                    }
                }

                // Music — audible, looping, independent of the video
                val audioPlayer = remember {
                    ExoPlayer.Builder(context).build().apply {
                        setMediaItem(
                            MediaItem.fromUri("android.resource://${context.packageName}/${vibe!!.trackRes}")
                        )
                        repeatMode = ExoPlayer.REPEAT_MODE_ONE
                        prepare()
                        playWhenReady = true
                    }
                }

                DisposableEffect(Unit) {
                    onDispose {
                        videoPlayer.release()
                        audioPlayer.release()
                    }
                }

                AndroidView(
                    factory = {
                        PlayerView(context).apply {
                            player = videoPlayer
                            useController = false
                            resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                            layoutParams = ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT
                            )
                        }
                    },
                    update = { playerView ->
                        playerView.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }

            Text(
                text = "←",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(20.dp)
                    .clickable { onBack() }
            )

            Row(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = vibe?.trackName ?: "",
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Filled.MusicNote,
                    contentDescription = null,
                    tint = Color.White
                )
            }

            Text(
                text = vibe?.quote ?: "",
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(20.dp)
            )
        }
    }
}