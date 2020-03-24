package kg.koronastaff.staffapp.models

data class TestQuestion(
        var id: Int,
        var title: String,
        var description: String,
        var choices: ArrayList<Choice>,
        var isLast: Boolean = false
){
    data class Choice(
            var id: Int,
            var sc_value: String,
            var isSend: Boolean = false
    )
}