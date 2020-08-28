package com.esmaeel.compo.data.models

data class PopularPersonsResponse(
    val page: Int? = 0,
    val results: ArrayList<Results?>? = arrayListOf(),
    val total_pages: Int? = 0,
    val total_results: Int? = 0
) {
    fun hasMorePages(): Boolean {
        return page!! < total_pages!!;
    }
}

data class Results(
    val adult: Boolean? = false,
    val gender: Int? = 0,
    val id: Int? = 0,
    val known_for: ArrayList<KnownFor?>? = arrayListOf(),
    val known_for_department: String? = "",
    val name: String? = "",
    val popularity: Double? = 0.0,
    val profile_path: String? = ""
)

data class KnownFor(
    val adult: Boolean? = false,
    val backdrop_path: String? = "",
    val first_air_date: String? = "",
    val genre_ids: ArrayList<Int?>? = arrayListOf(),
    val id: Int? = 0,
    val media_type: String? = "",
    val name: String? = "",
    val origin_country: ArrayList<String?>? = arrayListOf(),
    val original_language: String? = "",
    val original_name: String? = "",
    val original_title: String? = "",
    val overview: String? = "",
    val poster_path: String? = "",
    val release_date: String? = "",
    val title: String? = "",
    val video: Boolean? = false,
    val vote_average: Double? = 0.0,
    val vote_count: Int? = 0
)