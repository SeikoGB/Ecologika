package uz.itschool.ecologika.presentation.ItemRubrics

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import uz.itschool.ecologika.App
import uz.itschool.ecologika.model.Actions
import uz.itschool.ecologika.model.ByIdRequest
import uz.itschool.ecologika.model.RubricsFull
import uz.itschool.ecologika.model.data
import uz.itschool.ecologika.network.APIClient
import uz.itschool.ecologika.network.APIServise
import uz.itschool.ecologika.preference.Settings

class RubricsItemViewModel: ViewModel() {
    private val settings: Settings by lazy { Settings.getSettings(App.app) }
    private val appAPI: APIServise by lazy { APIClient.getInstance(App.app).create() }
    val rubric= MutableLiveData<ArrayList<RubricsFull>?>()


    fun getProblemItem(id: Int){
        var data=data(id.toString())
        var request=ByIdRequest("site",Actions.PROBLEMS_BY_ID.action,settings.getLanguage(),data)
        appAPI.getProblemsById(request).enqueue(object :Callback<ArrayList<RubricsFull>>{
            override fun onResponse(
                call: Call<ArrayList<RubricsFull>>,
                response: Response<ArrayList<RubricsFull>>,
            ) {
                var temp_body=response.body()
                if (response.isSuccessful && temp_body!=null){
                    rubric.value=temp_body
                }
            }

            override fun onFailure(call: Call<ArrayList<RubricsFull>>, t: Throwable) {

            }

        })
    }
    fun getInterestingItem(id: Int){
        var data=data(id.toString())
        var request=ByIdRequest("site",Actions.INTERESTINGS_BY_ID.action,settings.getLanguage(),data)
        appAPI.getInterestingsById(request).enqueue(object :Callback<ArrayList<RubricsFull>>{
            override fun onResponse(
                call: Call<ArrayList<RubricsFull>>,
                response: Response<ArrayList<RubricsFull>>,
            ) {
                var temp_body=response.body()
                if (response.isSuccessful && temp_body!=null){
                    rubric.value=temp_body
                }
            }

            override fun onFailure(call: Call<ArrayList<RubricsFull>>, t: Throwable) {

            }

        })
    }
    fun getDoYouKnowItem(id: Int){
        var data=data(id.toString())
        var request=ByIdRequest("site",Actions.DO_YOU_KNOW_BY_ID.action,settings.getLanguage(),data)
        appAPI.getDoYoKnowsById(request).enqueue(object :Callback<ArrayList<RubricsFull>>{
            override fun onResponse(
                call: Call<ArrayList<RubricsFull>>,
                response: Response<ArrayList<RubricsFull>>,
            ) {
                var temp_body=response.body()
                if (response.isSuccessful && temp_body!=null){
                    rubric.value=temp_body
                }
            }

            override fun onFailure(call: Call<ArrayList<RubricsFull>>, t: Throwable) {

            }

        })
    }
    fun getFolklorItem(id: Int){
        var data=data(id.toString())
        var request=ByIdRequest("site",Actions.FOLKLOR_BY_ID.action,settings.getLanguage(),data)
        appAPI.getFolklorsById(request).enqueue(object :Callback<ArrayList<RubricsFull>>{
            override fun onResponse(
                call: Call<ArrayList<RubricsFull>>,
                response: Response<ArrayList<RubricsFull>>,
            ) {
                var temp_body=response.body()
                if (response.isSuccessful && temp_body!=null){
                    rubric.value=temp_body
                }
            }

            override fun onFailure(call: Call<ArrayList<RubricsFull>>, t: Throwable) {

            }

        })
    }
    fun getEcoHistoryItem(id: Int){
        var data=data(id.toString())
        var request=ByIdRequest("site",Actions.ECO_HISTORY_BY_ID.action,settings.getLanguage(),data)
        appAPI.getEcoHistoriesById(request).enqueue(object :Callback<ArrayList<RubricsFull>>{
            override fun onResponse(
                call: Call<ArrayList<RubricsFull>>,
                response: Response<ArrayList<RubricsFull>>,
            ) {
                var temp_body=response.body()
                if (response.isSuccessful && temp_body!=null){
                    rubric.value=temp_body
                }
            }

            override fun onFailure(call: Call<ArrayList<RubricsFull>>, t: Throwable) {

            }

        })
    }
}