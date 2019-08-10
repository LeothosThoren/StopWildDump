package fr.leothosthoren.stopwilddump.data.models.lyon

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DataLandfillLyon(

    @Json(name = "features")
    val features: List<FeaturesItem?>? = null,

    @Json(name = "crs")
    val crs: Crs? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "type")
    val type: String? = null
)