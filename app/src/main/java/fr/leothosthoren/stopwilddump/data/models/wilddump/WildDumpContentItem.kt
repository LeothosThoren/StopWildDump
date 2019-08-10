package fr.leothosthoren.stopwilddump.data.models.wilddump

import com.squareup.moshi.Json
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
data class WildDumpContentItem(

    @Json(name = "concrete")
    val concrete: Boolean? = null,

    @Json(name = "glass")
    val glass: Boolean? = null,

    @Json(name = "other")
    val other: String? = null,

    @Json(name = "householdWaste")
    val householdWaste: Boolean? = null,

    @Json(name = "metal")
    val metal: Boolean? = null,

    @Json(name = "dangerous")
    val dangerous: Boolean? = null,

    @Json(name = "greenWaste")
    val greenWaste: Boolean? = null,

    @Json(name = "wildDumpType")
    val wildDumpType: String? = null,

    @Json(name = "volume")
    val volume: Int? = null,

    @Json(name = "plastic")
    val plastic: Boolean? = null,

    @Json(name = "homeAppliance")
    val homeAppliance: Boolean? = null,

    @Json(name = "wood")
    val wood: Boolean? = null,

    @Json(name = "clothing")
    val clothing: Boolean? = null,

    @Json(name = "rubble")
    val rubble: Boolean? = null,

    @Json(name = "tire")
    val tire: Boolean? = null
)