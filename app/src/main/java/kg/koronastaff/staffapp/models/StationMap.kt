package kg.koronastaff.staffapp.models

data class StationMap(
        var id: Int,
        var address: String,
        var telephone: String,
        var website: String,
        var lat: Double,
        var lon: Double
)