package fr.leothosthoren.stopwilddump.data.models.wildump_models

import com.squareup.moshi.Json
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
data class PartnersItem(

    @Json(name = "country")
    val country: String? = null,

    @Json(name = "town")
    val town: String? = null,

    @Json(name = "webLink")
    val webLink: String? = null,

    @Json(name = "cause")
    val cause: String? = null,

    @Json(name = "description")
    val description: String? = null,

    @Json(name = "adress")
    val adress: String? = null,

    @Json(name = "type")
    val type: String? = null,

    @Json(name = "ceo")
    val ceo: String? = null,

    @Json(name = "isActive")
    val isActive: Boolean? = null,

    @Json(name = "zipcode")
    val zipcode: Int? = null,

    @Json(name = "imageLink")
    val imageLink: String? = null,

    @Json(name = "contribution")
    val contribution: Int? = null,

    @Json(name = "iconLink")
    val iconLink: String? = null,

    @Json(name = "donationDate")
    val donationDate: String? = null,

    @Json(name = "company")
    val company: String? = null,

    @Json(name = "id")
    val id: Int? = null,

    @Json(name = "email")
    val email: String? = null
)