package kg.koronastaff.staffapp.models

data class City (
    var name: String,
    var pk: Int = 0,
    var region: CityRegion
){
    data class CityRegion(
        var name: String,
        var pk: Int
    )
}