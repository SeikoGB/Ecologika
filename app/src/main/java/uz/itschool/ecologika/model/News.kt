package uz.itschool.ecologika.model

import java.io.Serializable

data class News (
    val id: String,
    val title: String,
    val sdate: String,
    val annotation: String,
    val photo: String,
) :Serializable