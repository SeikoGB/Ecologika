package uz.itschool.ecologika.model

data class AuthResponse(
    var status:Int,
    var jwt:String,
    var dat:AuthData
)