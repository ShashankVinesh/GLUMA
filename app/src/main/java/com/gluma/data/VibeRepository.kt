package com.gluma.data

import com.gluma.R

object VibeRepository {

    val categories = listOf(
        Category("Nature", R.drawable.nature_bg, R.drawable.cat_nature),
        Category("Space", R.drawable.space_bg, R.drawable.cat_space),
        Category("Anime", R.drawable.anime_bg , R.drawable.cat_anime),
        Category("Games", R.drawable.games_bg , R.drawable.cat_game),
        Category("Rain", R.drawable.rain_bg , R.drawable.cat_rain),
        Category("Cozy", R.drawable.cozy_bg , R.drawable.cat_cozy)
    )

    val vibesByCategory = mapOf(

        "Anime" to listOf(
            Vibe(
                id = "frieren",
                name = "Frieren",
                backgroundUrl = "https://plvxnpfjjdmpcqymgdbm.supabase.co/storage/v1/object/public/BgVideos/Anime/frieren.mp4",
                trackRes = R.raw.time_flow_ever_onward,
                thumbnailUrl = "https://plvxnpfjjdmpcqymgdbm.supabase.co/storage/v1/object/public/Thumnails/anime/Screenshot%202026-08-25%20214254.png",
                quote = "Life is a journey. ",
                trackName = "Time Flows Ever Onward"
            ) ,
            Vibe(
                id = "bleach",
                name = "Bleach",
                backgroundUrl = "https://plvxnpfjjdmpcqymgdbm.supabase.co/storage/v1/object/public/BgVideos/Anime/bleach%20(1).mp4",
                trackRes = R.raw.u_werent_here,
                thumbnailUrl = "https://plvxnpfjjdmpcqymgdbm.supabase.co/storage/v1/object/public/Thumnails/anime/Screenshot%202026-08-25%20214221.png",
                quote = "Fight For Your Loved Ones.",
                trackName = "U weren't here , I really miss you"
            )
        ),
        "Space" to listOf(
            Vibe(
                id = "space",
                name = "Earth",
                backgroundUrl = "https://plvxnpfjjdmpcqymgdbm.supabase.co/storage/v1/object/public/BgVideos/space/space.mov",
                trackRes = R.raw.still_with_you,
                thumbnailUrl = "https://plvxnpfjjdmpcqymgdbm.supabase.co/storage/v1/object/public/Thumnails/space/Screenshot%202026-08-25%20214706.png",
                quote = "Does it really matters?.",
                trackName = "Still with you"
            )
        ),
        "Nature" to listOf(
            Vibe(
                id = "nature",
                name = "Nature",
                backgroundUrl = "https://plvxnpfjjdmpcqymgdbm.supabase.co/storage/v1/object/public/BgVideos/nature/nature2.mov",
                trackRes = R.raw.chubina,
                thumbnailUrl = "https://plvxnpfjjdmpcqymgdbm.supabase.co/storage/v1/object/public/Thumnails/nature/Screenshot%202026-08-25%20214638.png",
                quote = "Peace.",
                trackName = "Chubina"
            )
        ),
        "Rain" to listOf(
            Vibe(
                id = "rain1",
                name = "Rainy Street",
                backgroundUrl = "https://plvxnpfjjdmpcqymgdbm.supabase.co/storage/v1/object/public/BgVideos/rain/rian1.mp4",
                trackRes = R.raw.still_with_you,
                thumbnailUrl = "https://plvxnpfjjdmpcqymgdbm.supabase.co/storage/v1/object/public/Thumnails/rain/Screenshot%202026-08-25%20214655.png",
                quote = "Everything is gonna be alright.",
                trackName = "Still with you "
            ) ,
            Vibe(
                id = "rain2",
                name = "Rainy Apartment",
                backgroundUrl = "https://plvxnpfjjdmpcqymgdbm.supabase.co/storage/v1/object/public/BgVideos/rain/rain2.mp4",
                trackRes = R.raw.still_with_you,
                thumbnailUrl = "https://plvxnpfjjdmpcqymgdbm.supabase.co/storage/v1/object/public/Thumnails/rain/Screenshot%202026-08-25%20214648.png",
                quote = "Sometimes thinking nothing is everyhting.",
                trackName = "still with you "
            )
        ),
        "Cozy" to listOf(
            Vibe(
                id = "cozy",
                name = "Cozy",
                backgroundUrl = "https://plvxnpfjjdmpcqymgdbm.supabase.co/storage/v1/object/public/BgVideos/Cozy/cozy2.mp4",
                trackRes = R.raw.atomic_blue,
                thumbnailUrl = "https://plvxnpfjjdmpcqymgdbm.supabase.co/storage/v1/object/public/Thumnails/cozy/Screenshot%202026-08-25%20214509.png",
                quote = "Chillin~.",
                trackName = "Atomic Blue"
            )
        ),
        "Games" to listOf(
            Vibe(
                id = "minecraft",
                name = "Minecraft",
                backgroundUrl = "https://plvxnpfjjdmpcqymgdbm.supabase.co/storage/v1/object/public/BgVideos/Game/Minecraft1%20(1).mp4",
                trackRes = R.raw.minecraftc418,
                thumbnailUrl = "https://plvxnpfjjdmpcqymgdbm.supabase.co/storage/v1/object/public/Thumnails/game/Screenshot%202026-08-25%20214548.png",
                quote = "How did we get there?.",
                trackName = "Mineraft C418"
            ) ,
            Vibe(
                id = "sekiro",
                name = "Sekiro",
                backgroundUrl = "https://plvxnpfjjdmpcqymgdbm.supabase.co/storage/v1/object/public/BgVideos/Game/Sekiro1.mp4",
                trackRes = R.raw.yad,
                thumbnailUrl = "https://plvxnpfjjdmpcqymgdbm.supabase.co/storage/v1/object/public/Thumnails/game/Screenshot%202026-08-25%20214557.png",
                quote = "Hesitation is defeat.",
                trackName = "Yad"
            ) ,
            Vibe(
                id = "eldenring",
                name = "Elden Ring",
                backgroundUrl = "https://plvxnpfjjdmpcqymgdbm.supabase.co/storage/v1/object/public/BgVideos/Game/eldenring1.mp4",
                trackRes = R.raw.golden_brown,
                thumbnailUrl = "https://plvxnpfjjdmpcqymgdbm.supabase.co/storage/v1/object/public/Thumnails/game/Screenshot%202026-08-25%20214538.png",
                quote = "Path to a different life",
                trackName = "Golden Brown"
            )
        )
    )

    fun getVibes(category: String): List<Vibe> = vibesByCategory[category].orEmpty()

    fun getVibeById(vibeId: String): Vibe? =
        vibesByCategory.values.flatten().find { it.id == vibeId }
}