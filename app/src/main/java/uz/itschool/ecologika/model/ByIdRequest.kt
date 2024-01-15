package uz.itschool.ecologika.model

data class ByIdRequest(
    var method:String="site",
    var action: String,
    var language:Int=3,
    var data:data
    )
