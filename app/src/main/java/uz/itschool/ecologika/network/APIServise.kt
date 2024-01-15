package uz.itschool.ecologika.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import uz.itschool.ecologika.model.Auth
import uz.itschool.ecologika.model.AuthResponse
import uz.itschool.ecologika.model.ByIdRequest
import uz.itschool.ecologika.model.Plant
import uz.itschool.ecologika.model.ProblemFull
import uz.itschool.ecologika.model.ProblemMini
import uz.itschool.ecologika.model.Quote
import uz.itschool.ecologika.model.Request

interface APIServise {
    @POST("/api.php")
    fun getRedBookPlants(@Body request: Request):ArrayList<Plant>

    @POST("/auth.php")
    fun getToken(@Body user:Auth):Call<AuthResponse>

    @POST("/api.php")
    fun getRedBookAnimals(@Body request: Request):ArrayList<Plant>

    @POST("/api.php")
    fun getQuotes(@Body request: Request):Call<ArrayList<Quote>>

    @POST("/api.php")
    fun getProblemsById(@Body byIdRequest: ByIdRequest):Call<ArrayList<ProblemFull>>

    @POST("/api.php")
    fun getProblems(@Body request: Request):Call<ArrayList<ProblemMini>>

}