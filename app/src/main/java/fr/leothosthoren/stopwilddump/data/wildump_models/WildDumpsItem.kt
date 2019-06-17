package fr.leothosthoren.stopwilddump.data.wildump_models

import com.squareup.moshi.Json
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
data class WildDumpsItem(

    @Json(name = "country")
    val country: String? = null,

    @Json(name = "image")
    val image: String? = null,

    @Json(name = "town")
    val town: String? = null,

    @Json(name = "webLink")
    val webLink: String? = null,

    @Json(name = "latitude")
    val latitude: Double? = null,

    @Json(name = "cleaner")
    val cleaner: List<CleanerItem?>? = null,

    @Json(name = "icon")
    val icon: String? = null,

    @Json(name = "cleanningDate")
    val cleanningDate: String? = null,

    @Json(name = "description")
    val description: String? = null,

    @Json(name = "reporter")
    val reporter: List<ReporterItem?>? = null,

    @Json(name = "type")
    val type: String? = null,

    @Json(name = "isActive")
    val isActive: Boolean? = null,

    @Json(name = "zipcode")
    val zipcode: Int? = null,

    @Json(name = "wildDumpContent")
    val wildDumpContent: List<WildDumpContentItem?>? = null,

    @Json(name = "feature")
    val feature: String? = null,

    @Json(name = "reportDate")
    val reportDate: String? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "id")
    val id: Int? = null,

    @Json(name = "geometryType")
    val geometryType: String? = null,

    @Json(name = "longitude")
    val longitude: Double? = null,

    @Json(name = "NewKey")
    val newKey: NewKey? = null
)