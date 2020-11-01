package com.nguyen.tccovid19k

import com.google.gson.annotations.SerializedName
import org.json.JSONArray
import org.json.JSONObject
import java.io.Serializable

class Countries(jsonObject: JSONObject) {
    @SerializedName("data")
    var data: Data = Data(jsonObject.getJSONObject("data"))
}

class Data(jsonObject: JSONObject) {
    @SerializedName("paginationMeta")
    var pagination: Pagination = Pagination(jsonObject.getJSONObject("paginationMeta"))

    @SerializedName("last_update")
    var lastUpdate: String = jsonObject.getString("last_update")

    @SerializedName("rows")
    var countries: List<Country> = Country.fromJsonArray(jsonObject.getJSONArray("rows"))
}

class Pagination(jsonObject: JSONObject) {
    @SerializedName("currentPage")
    var currentPage = jsonObject.getInt("currentPage")

    @SerializedName("totalPages")
    var totalPages = jsonObject.getInt("totalPages")
}

class Country(jsonObject: JSONObject) : Serializable {
    @SerializedName("country")
    var country: String = jsonObject.getString("country")

    @SerializedName("country_abbreviation")
    var countryAbbreviation: String = jsonObject.getString("country_abbreviation")

    @SerializedName("total_cases")
    var totalCases: String = jsonObject.getString("total_cases")

    @SerializedName("total_deaths")
    var totalDeaths: String = jsonObject.getString("total_deaths")

    @SerializedName("total_recovered")
    var totalRecovered: String = jsonObject.getString("total_recovered")

    @SerializedName("active_cases")
    var activeCases: String = jsonObject.getString("active_cases")

    @SerializedName("flag")
    var flag: String = jsonObject.getString("flag")

    companion object {
        fun fromJsonArray(jsonArray: JSONArray) : List<Country> {
            val countries = mutableListOf<Country>()
            for (i in 0 until jsonArray.length()) {
                val country = Country(jsonArray.getJSONObject(i))
                countries.add(country)
            }
            return countries
        }
    }
}