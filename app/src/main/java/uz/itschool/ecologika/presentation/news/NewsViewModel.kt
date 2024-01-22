package uz.itschool.ecologika.presentation.news

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
import uz.itschool.ecologika.model.ProblemFull
import uz.itschool.ecologika.model.Quote
import uz.itschool.ecologika.model.Request
import uz.itschool.ecologika.network.APIClient
import uz.itschool.ecologika.network.APIServise
import uz.itschool.ecologika.preference.Settings

class NewsViewModel:ViewModel(){
    val newsLiveData= MutableLiveData<ArrayList<News>?>()
    private val appAPI: APIServise by lazy { APIClient.getInstance(App.app).create() }
    private val settings: Settings by lazy { Settings.getSettings(App.app) }


    fun getNews(){
        var request=Request(method = "site",Actions.NEWS.action,settings.getLanguage())
        appAPI.getNews(request).enqueue(object :Callback<ArrayList<News>>{
            override fun onResponse(
                call: Call<ArrayList<News>>,
                response: Response<ArrayList<News>>,
            ) {
                var body=response.body()
                if (response.isSuccessful && body!=null){
                    newsLiveData.value = body
                }
            }

            override fun onFailure(call: Call<ArrayList<News>>, t: Throwable) {

            }

        })
    }
}