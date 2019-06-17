package fr.leothosthoren.stopwilddump.data.wildump_models

import com.squareup.moshi.Json
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
data class ReporterItem(

    @Json(name = "firstName")
    val firstName: String? = null,

    @Json(name = "lastName")
    val lastName: String? = null,

    @Json(name = "zipCode")
    val zipCode: Int? = null,

    @Json(name = "country")
    val country: String? = null,

    @Json(name = "website")
    val website: String? = null,

    @Json(name = "phoneNumber")
    val phoneNumber: String? = null,

    @Json(name = "town")
    val town: String? = null,

    @Json(name = "adress")
    val adress: String? = null,

    @Json(name = "email")
    val email: String? = null
)