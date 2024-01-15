package uz.itschool.ecologika.presentation.problems_fragment

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
import uz.itschool.ecologika.model.Request
import uz.itschool.ecologika.model.data
import uz.itschool.ecologika.network.APIClient
import uz.itschool.ecologika.network.APIServise
import uz.itschool.ecologika.preference.Settings

class problemsViewModel:ViewModel() {
    private val appAPI: APIServise by lazy { APIClient.getInstance(App.app).create() }
    private val settings: Settings by lazy { Settings.getSettings(App.app) }
    val problemsLiveData= MutableLiveData<ArrayList<ProblemFull>?>()

    fun getProbems(limit:Int){
        var request= Request(method = "site", action = Actions.PROBLEMS.action, language = settings.getLanguage())
        var temp_limit=limit
        var problems = ArrayList<ProblemFull>()
        appAPI.getProblems(request).enqueue(object: Callback<ArrayList<ProblemMini>> {
            override fun onResponse(
                call: Call<ArrayList<ProblemMini>>,
                response: Response<ArrayList<ProblemMini>>, )
            {
                var body=response.body()
                if (response.isSuccessful && body!=null){
                    if (temp_limit>=body.size)temp_limit=body.size
                    for (i in 0..limit-1){
                        var data= data(body[i].id)
                        Log.d("TAG", "onResponse: ${body.toString()}")
                        var problemRequest= ByIdRequest("site",
                            Actions.PROBLEMS_BY_ID.action,settings.getLanguage(),data)
                        appAPI.getProblemsById(problemRequest).enqueue(object :
                            Callback<ArrayList<ProblemFull>> {
                            override fun onResponse(
                                call: Call<ArrayList<ProblemFull>>,
                                response: Response<ArrayList<ProblemFull>>,
                            ) {
                                var body=response.body()
                                if (response.isSuccessful && body!=null){
                                    Log.d("TAG", "onResponse11: ${body[0].title}")
                                    problems.addAll(body)
                                    Log.d("TAGgg", "onResponse: ${problems.toString()}")
                                    problemsLiveData.value=problems
                                }
                            }

                            override fun onFailure(
                                call: Call<ArrayList<ProblemFull>>,
                                t: Throwable,
                            ) {
                            }

                        })
                    }
                }
            }

            override fun onFailure(call: Call<ArrayList<ProblemMini>>, t: Throwable) {

            }
        })

    }
}