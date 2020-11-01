package com.nguyen.tccovid19k

import com.google.gson.annotations.SerializedName
import org.json.JSONObject

public class WorldGson {
    @SerializedName("data")
    lateinit var data: World
}

class World(jsonObject: JSONObject) {
    @SerializedName("total_cases")
    var totalCases: String
    @SerializedName("recovery_cases")
    var recoveryCases: String
    @SerializedName("death_cases")
    var deathCases: String
    @SerializedName("last_update")
    var lastUpdate: String
    @SerializedName("currently_infected")
    var currentlyInfected: String

    init {
        val data = jsonObject.getJSONObject("data")
        totalCases = data.getString("total_cases")
        recoveryCases = data.getString("recovery_cases")
        deathCases = data.getString("death_cases")
        lastUpdate = data.getString("last_update")
        currentlyInfected = data.getString("currently_infected")
    }
}