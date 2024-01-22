package uz.itschool.ecologika.model

import java.io.Serializable

data class RubricsFull(
    var id:Int,
    var title:String,
    var photo:String,
    var content:String

):Serializable