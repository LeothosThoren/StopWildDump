package fr.leothosthoren.stopwilddump.data.models.landfill_models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FeaturesItem(

    @Json(name = "geometry")
    val geometry: Geometry? = null,

    @Json(name = "type")
    val type: String? = null,

    @Json(name = "properties")
    val properties: Properties? = null
)