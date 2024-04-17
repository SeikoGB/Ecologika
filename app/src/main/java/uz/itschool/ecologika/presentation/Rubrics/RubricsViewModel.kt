package uz.itschool.ecologika.presentation.Rubrics

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import uz.itschool.ecologika.App
import uz.itschool.ecologika.model.Actions
import uz.itschool.ecologika.model.News
import uz.itschool.ecologika.model.Request
import uz.itschool.ecologika.model.RubricsMini
import uz.itschool.ecologika.model.Screens
import uz.itschool.ecologika.network.APIClient
import uz.itschool.ecologika.network.APIServise
import uz.itschool.ecologika.preference.Settings

class RubricsViewModel():ViewModel() {

    val interestingsLiveData= MutableLiveData<ArrayList<RubricsMini>?>()
    val doyouknowsLiveData= MutableLiveData<ArrayList<RubricsMini>?>()
    val folklorsLiveData= MutableLiveData<ArrayList<RubricsMini>?>()
    val problemsLiveData= MutableLiveData<ArrayList<RubricsMini>?>()
    val ecohistoryLiveData= MutableLiveData<ArrayList<RubricsMini>?>()
    private val settings: Settings by lazy { Settings.getSettings(App.app) }
    private val appAPI: APIServise by lazy { APIClient.getInstance(App.app).create() }

    fun loadInterestings(){
        var request=Request(method = "site", Actions.INTERESTINGS.action,settings.getLanguage())
        appAPI.getInterestings(request).enqueue(object :Callback<ArrayList<RubricsMini>>{
            override fun onResponse(
                call: Call<ArrayList<RubricsMini>>,
                response: Response<ArrayList<RubricsMini>>,
            ) {
                var body=response.body()
                if (response.isSuccessful && body!=null){
                    interestingsLiveData.value = body
                }
            }

            override fun onFailure(call: Call<ArrayList<RubricsMini>>, t: Throwable) {

            }
        })
    }
    fun loadDoYouKnows(){
        var request=Request(method = "site", Actions.DO_YOU_KNOWS.action,settings.getLanguage())
        appAPI.getInterestings(request).enqueue(object :Callback<ArrayList<RubricsMini>>{
            override fun onResponse(
                call: Call<ArrayList<RubricsMini>>,
                response: Response<ArrayList<RubricsMini>>,
            ) {
                var body=response.body()
                if (response.isSuccessful && body!=null){
                    doyouknowsLiveData.value = body
                }
            }

            override fun onFailure(call: Call<ArrayList<RubricsMini>>, t: Throwable) {

            }
        })
    }
    fun loadEcoHistories(){
        var request=Request(method = "site", Actions.ECOHISTORY.action,settings.getLanguage())
        appAPI.getInterestings(request).enqueue(object :Callback<ArrayList<RubricsMini>>{
            override fun onResponse(
                call: Call<ArrayList<RubricsMini>>,
                response: Response<ArrayList<RubricsMini>>,
            ) {
                var body=response.body()
                if (response.isSuccessful && body!=null){
                    ecohistoryLiveData.value = body
                }
            }

            override fun onFailure(call: Call<ArrayList<RubricsMini>>, t: Throwable) {

            }
        })
    }
    fun loadEcoFolklors(){
        var request=Request(method = "site", Actions.FOLKLOR.action,settings.getLanguage())
        appAPI.getInterestings(request).enqueue(object :Callback<ArrayList<RubricsMini>>{
            override fun onResponse(
                call: Call<ArrayList<RubricsMini>>,
                response: Response<ArrayList<RubricsMini>>,
            ) {
                var body=response.body()
                if (response.isSuccessful && body!=null){
                    folklorsLiveData.value = body
                }
            }

            override fun onFailure(call: Call<ArrayList<RubricsMini>>, t: Throwable) {

            }
        })
    }
    fun loadecoProblems(){
        var request=Request(method = "site", Actions.PROBLEMS.action,settings.getLanguage())
        appAPI.getInterestings(request).enqueue(object :Callback<ArrayList<RubricsMini>>{
            override fun onResponse(
                call: Call<ArrayList<RubricsMini>>,
                response: Response<ArrayList<RubricsMini>>,
            ) {
                var body=response.body()
                if (response.isSuccessful && body!=null){
                    problemsLiveData.value = body
                }
            }

            override fun onFailure(call: Call<ArrayList<RubricsMini>>, t: Throwable) {

            }
        })
    }
    fun getLayout(){
        settings.setLayout(Screens.HOME.screen)
    }
    init{
        getLayout()
    }
}