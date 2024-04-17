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
import uz.itschool.ecologika.model.CategoryRequest
import uz.itschool.ecologika.model.Media
import uz.itschool.ecologika.model.News
import uz.itschool.ecologika.model.Organisms
import uz.itschool.ecologika.model.Photo
import uz.itschool.ecologika.model.Plant
import uz.itschool.ecologika.model.ProblemFull
import uz.itschool.ecologika.model.ProblemMini
import uz.itschool.ecologika.model.Quote
import uz.itschool.ecologika.model.RedBookRequest
import uz.itschool.ecologika.model.Request
import uz.itschool.ecologika.model.RubricsFull
import uz.itschool.ecologika.model.RubricsMini

interface APIServise {
    @POST("/api.php")
    fun getRedBookPlants(@Body request: Request):ArrayList<Plant>

    @POST("/auth.php")
    fun getToken(@Body user:Auth):Call<AuthResponse>

    @POST("/api.php")
    fun getRedBookAnimals(@Body request: Request):ArrayList<Plant>

    @POST ("/api.php")
    fun getNews(@Body request: Request):Call<ArrayList<News>>

    @POST("/api.php")
    fun getQuotes(@Body request: Request):Call<ArrayList<Quote>>

    @POST("/api.php")
    fun getProblemsById(@Body byIdRequest: ByIdRequest):Call<ArrayList<RubricsFull>>
    @POST("/api.php")
    fun getEcoHistoriesById(@Body byIdRequest: ByIdRequest):Call<ArrayList<RubricsFull>>
    @POST("/api.php")
    fun getInterestingsById(@Body byIdRequest: ByIdRequest):Call<ArrayList<RubricsFull>>
    @POST("/api.php")
    fun getDoYoKnowsById(@Body byIdRequest: ByIdRequest):Call<ArrayList<RubricsFull>>
    @POST("/api.php")
    fun getFolklorsById(@Body byIdRequest: ByIdRequest):Call<ArrayList<RubricsFull>>
    @POST("/api.php")
    fun getProblems(@Body request: Request):Call<ArrayList<RubricsMini>>

    @POST("/api.php")
    fun getMedia(@Body request: Request):Call<ArrayList<Media>>
    @POST("/api.php")
    fun getReserves(@Body request: Request):Call<ArrayList<Media>>

    @POST("/api.php")
    fun getPhotogallery(@Body request: ByIdRequest):Call<ArrayList<Photo>>

    @POST("/api.php")
    fun getInterestings(@Body request: Request):Call<ArrayList<RubricsMini>>
    @POST("/api.php")
    fun getDoYoyKnows(@Body request: Request):Call<ArrayList<RubricsMini>>
    @POST("/api.php")
    fun getFolklors(@Body request: Request):Call<ArrayList<RubricsMini>>
    @POST("/api.php")
    fun getEcoHistories(@Body request: Request):Call<ArrayList<RubricsMini>>
    @POST("/api.php")
    fun getCategories(@Body request: CategoryRequest):Call<ArrayList<Media>>

    @POST("/api.php")
    fun getOrganisms(@Body request: RedBookRequest):Call<ArrayList<Organisms>>

}
