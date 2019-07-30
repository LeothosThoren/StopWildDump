package fr.leothosthoren.stopwilddump.data.models.landfill_models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Geometry(

    @Json(name = "coordinates")
    val coordinates: List<Double?>? = null,

    @Json(name = "type")
    val type: String? = null
)