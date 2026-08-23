package com.gluma.data

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

object SupabaseClient {
    val client = createSupabaseClient(
        supabaseUrl = "https://plvxnpfjjdmpcqymgdbm.supabase.co",
        supabaseKey = "sb_publishable_rb_n66Mtz3tmYzBevTGA-g_xq8rHNI9"
    ) {
        install(Postgrest)
    }
}