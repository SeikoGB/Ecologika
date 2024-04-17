package uz.itschool.ecologika.presentation.Quotes

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import uz.itschool.ecologika.App
import uz.itschool.ecologika.model.Actions
import uz.itschool.ecologika.model.Quote
import uz.itschool.ecologika.model.Request
import uz.itschool.ecologika.model.Screens
import uz.itschool.ecologika.network.APIClient
import uz.itschool.ecologika.network.APIServise
import uz.itschool.ecologika.preference.Settings

class QuoteViewModel : ViewModel(){
    val quotesLiveData= MutableLiveData<ArrayList<Quote>?>()

    private val appAPI: APIServise by lazy { APIClient.getInstance(App.app).create() }
    private val settings: Settings by lazy { Settings.getSettings(App.app) }
    var quotes= ArrayList<Quote>()
    fun getQuotes(){

        var request= Request(method = "site", action = Actions.QUOTES.action,settings.getLanguage())
        appAPI.getQuotes(request).enqueue(object : Callback<ArrayList<Quote>> {
            override fun onResponse(
                call: Call<ArrayList<Quote>>,
                response: Response<ArrayList<Quote>>,
            ){
                var body=response.body()
                if (response.isSuccessful && body!=null){
                    quotes.addAll(body)
                    Log.d("TAGgg", "onResponse: ${quotes.toString()}")
                    quotesLiveData.value = body
                }
            }
            override fun onFailure(call: Call<ArrayList<Quote>>, t: Throwable) {

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