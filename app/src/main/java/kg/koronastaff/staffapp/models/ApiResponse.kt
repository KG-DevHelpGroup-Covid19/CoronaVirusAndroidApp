package kg.koronastaff.staffapp.models

class ApiResponse<T>(
        var results: T,
        var next: Any,
        var previous: Any,
        var count: Int
)