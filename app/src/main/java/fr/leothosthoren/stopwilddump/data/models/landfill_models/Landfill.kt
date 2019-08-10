package fr.leothosthoren.stopwilddump.data.models.landfill_models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Landfill(

    @Json(name = "features")
    val features: List<FeaturesItem?>? = null,

    @Json(name = "type")
    val type: String? = null
)