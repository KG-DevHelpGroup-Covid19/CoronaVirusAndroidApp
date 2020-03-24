package kg.koronastaff.staffapp.models

data class TestQuestion(
        var id: Int,
        var title: String,
        var description: String,
        var choices: ArrayList<Choice>
){
    data class Choice(
            var id: Int,
            var sc_value: String
    )
}