package fr.leothosthoren.stopwilddump.data.models.landfill_models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Properties(

    @Json(name = "departement")
    val departement: Int? = null,

    @Json(name = "huiles")
    val huiles: String? = null,

    @Json(name = "dechets_dangereux0")
    val dechetsDangereux0: String? = null,

    @Json(name = "commune")
    val commune: String? = null,

    @Json(name = "jours_d_ouverture_particuliers")
    val joursDOuvertureParticuliers: String? = null,

    @Json(name = "textiles")
    val textiles: String? = null,

    @Json(name = "dechets_non_dangereux")
    val dechetsNonDangereux: String? = null,

    @Json(name = "dechets_inertes")
    val dechetsInertes: String? = null,

    @Json(name = "autres")
    val autres: String? = null,

    @Json(name = "accueil_des_professionnels")
    val accueilDesProfessionnels: String? = null,

    @Json(name = "dechets_dangereux")
    val dechetsDangereux: String? = null,

    @Json(name = "maitre_d_ouvrage")
    val maitreDOuvrage: String? = null,

    @Json(name = "pneus")
    val pneus: String? = null,

    @Json(name = "bois")
    val bois: String? = null,

    @Json(name = "ferrailles")
    val ferrailles: String? = null,

    @Json(name = "papiers_graphiques")
    val papiersGraphiques: String? = null,

    @Json(name = "cartons")
    val cartons: String? = null,

    @Json(name = "code_postal")
    val codePostal: Int? = null,

    @Json(name = "wgs84")
    val wgs84: List<Double?>? = null,

    @Json(name = "dechets_d_equipements_electriques_et_electroniques")
    val dechetsDEquipementsElectriquesEtElectroniques: String? = null,

    @Json(name = "piles_et_accumulateurs_et_batteries")
    val pilesEtAccumulateursEtBatteries: String? = null,

    @Json(name = "amiante")
    val amiante: String? = null,

    @Json(name = "nom_de_la_decheterie")
    val nomDeLaDecheterie: String? = null,

    @Json(name = "dechets_verts")
    val dechetsVerts: String? = null,

    @Json(name = "dechets_d_activites_de_soin_a_risques_infectieux")
    val dechetsDActivitesDeSoinARisquesInfectieux: String? = null,

    @Json(name = "adresse_1")
    val adresse1: String? = null,

    @Json(name = "adresse_2")
    val adresse2: String? = null,

    @Json(name = "jours_d_ouverture_artisans")
    val joursDOuvertureArtisans: String? = null,

    @Json(name = "gravats")
    val gravats: String? = null,

    @Json(name = "tout_venant")
    val toutVenant: String? = null,

    @Json(name = "accueil_des_services_techniques")
    val accueilDesServicesTechniques: String? = null,

    @Json(name = "zone_dediee_au_reemploi")
    val zoneDedieeAuReemploi: String? = null,

    @Json(name = "code_service_sinoe")
    val codeServiceSinoe: Int? = null
)