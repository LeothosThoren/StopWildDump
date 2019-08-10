package fr.leothosthoren.stopwilddump.data.models.lyon

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Properties(

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "gml_id")
    val gmlId: String? = null,

    @Json(name = "code_postal")
    val codePostal: String? = null,

    @Json(name = "voie")
    val voie: String? = null,

    @Json(name = "gid")
    val gid: String? = null,

    @Json(name = "gestionnaire")
    val gestionnaire: String? = null,

    @Json(name = "commune")
    val commune: String? = null,

    @Json(name = "identifiant")
    val identifiant: String? = null,

    @Json(name = "code_insee")
    val codeInsee: String? = null,

    @Json(name = "telephone")
    val telephone: String? = null,

    @Json(name = "numerodansvoie")
    val numerodansvoie: String? = null
)