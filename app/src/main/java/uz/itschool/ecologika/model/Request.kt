package uz.itschool.ecologika.model


data class Request (
    var method:String="site",
    var action: String,
    var language:Int=3,

    )