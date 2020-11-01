package com.nguyen.tccovid19k

import android.content.Context
import com.google.android.gms.maps.model.LatLng
import com.opencsv.CSVReader
import java.io.InputStreamReader

public class Coordinates(context: Context, filename: String) {
    var coordinates = mutableListOf<Coordinate>()

    init {
        val stream = InputStreamReader(context.assets.open(filename))
        val reader = CSVReader(stream)
        var nextLine: Array<String>
        do {
            nextLine = reader.readNext()
            nextLine?.let {
                val coordinate = Coordinate(it)
                coordinates.add(coordinate)
            }
        } while (nextLine != null)
        stream.close()
    }

    fun toLatLng(abbreviation: String) : LatLng? {
        val index = search(abbreviation)
        if (index != null) {
            val coordinate = coordinates[index]
            return LatLng(coordinate.latitude, coordinate.longitude)
        } else {
            return null
        }
    }

    private fun search(abbreviation: String): Int? {
        var left = 0
        var right = coordinates.size - 1
        while (left <= right) {
            val middle = left + (right - left) / 2
            if (coordinates[middle].abbreviation == abbreviation) {
                return middle
            } else if (coordinates[middle].abbreviation < abbreviation) {
                left = middle + 1
            } else {
                right = middle - 1
            }
        }
        return null
    }
}

class Coordinate(fields: Array<String>) {
    val abbreviation = fields[0]
    val latitude = fields[1].toDouble()
    val longitude = fields[2].toDouble()
}