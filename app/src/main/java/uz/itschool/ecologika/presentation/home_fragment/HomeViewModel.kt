package uz.itschool.ecologika.presentation.home_fragment

import android.media.session.MediaSession.Token
import android.provider.SyncStateContract
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
import uz.itschool.ecologika.model.ProblemFull
import uz.itschool.ecologika.model.ProblemMini
import uz.itschool.ecologika.model.Quote
import uz.itschool.ecologika.model.Request
import uz.itschool.ecologika.model.RubricsFull
import uz.itschool.ecologika.model.RubricsMini
import uz.itschool.ecologika.model.Screens
import uz.itschool.ecologika.model.data
import uz.itschool.ecologika.network.APIClient
import uz.itschool.ecologika.network.APIServise
import uz.itschool.ecologika.preference.Settings

class HomeViewModel:ViewModel() {
    private val appAPI: APIServise by lazy { APIClient.getInstance(App.app).create() }
    private val settings: Settings by lazy { Settings.getSettings(App.app) }
    val problemsLiveData=MutableLiveData<ArrayList<RubricsFull>?>()
    val quotesLiveData=MutableLiveData<ArrayList<Quote>?>()

    fun getQuotes(){
        var quotes= ArrayList<Quote>()
        var request=Request(method = "site", action = Actions.QUOTES.action,settings.getLanguage())
        appAPI.getQuotes(request).enqueue(object :Callback<ArrayList<Quote>>{
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
    fun getProbems(limit:Int){
        var request=Request(method = "site", action = Actions.PROBLEMS.action, language = settings.getLanguage())
        var temp_limit=limit
        var problems = ArrayList<RubricsFull>()
        appAPI.getProblems(request).enqueue(object:Callback<ArrayList<RubricsMini>>{

            override fun onResponse(
                call: Call<ArrayList<RubricsMini>>,
                response: Response<ArrayList<RubricsMini>>,
            ) {
                var body=response.body()
                if (response.isSuccessful && body!=null){
                    if (temp_limit>=body.size)temp_limit=body.size
                    for (i in 0..limit-1){
                        Log.d("TAG", "onResponse: ${body.toString()}")
                        var data=data(body[i].id.toString())
                        var problemRequest=ByIdRequest("site",Actions.PROBLEMS_BY_ID.action,settings.getLanguage(),data)

                        appAPI.getProblemsById(problemRequest).enqueue(object :Callback<ArrayList<RubricsFull>>{
                            override fun onResponse(
                                call: Call<ArrayList<RubricsFull>>,
                                response: Response<ArrayList<RubricsFull>>,
                            ) {
                                var temp_body=response.body()
                                if (response.isSuccessful && temp_body!=null){
                                    Log.d("TAG", "onResponse11: ${temp_body[0].title}")
                                    problems.addAll(temp_body)
                                    Log.d("TAGgg", "onResponse: ${problems.toString()}")
                                    problemsLiveData.value=problems
                                }
                            }

                            override fun onFailure(
                                call: Call<ArrayList<RubricsFull>>,
                                t: Throwable,
                            ) {
                                TODO("Not yet implemented")
                            }


                        })
                    }
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