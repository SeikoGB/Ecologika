package uz.itschool.ecologika.presentation.organisms

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import uz.itschool.ecologika.App
import uz.itschool.ecologika.model.Actions
import uz.itschool.ecologika.model.CategoryRequest
import uz.itschool.ecologika.model.Media
import uz.itschool.ecologika.model.Screens
import uz.itschool.ecologika.model.newdata
import uz.itschool.ecologika.network.APIClient
import uz.itschool.ecologika.network.APIServise
import uz.itschool.ecologika.preference.Settings

class OrganismsViewModel: ViewModel() {
    val categoriesLiveData= MutableLiveData<ArrayList<Media>?>()

    private val appAPI: APIServise by lazy { APIClient.getInstance(App.app).create() }
    private val settings: Settings by lazy { Settings.getSettings(App.app) }
    fun getCategories( id:Int){
        var  data= newdata(id)
        var request= CategoryRequest(method = "site", action = Actions.RED_BOOK_CATEGORIES.action,settings.getLanguage(),data)
        appAPI.getCategories(request).enqueue(object : Callback<ArrayList<Media>> {
            override fun onResponse(
                call: Call<ArrayList<Media>>,
                response: Response<ArrayList<Media>>,
            ) {
                var body=response.body()
                if (response.isSuccessful && body!=null){
                    categoriesLiveData.value = body
                }

            }

            override fun onFailure(call: Call<ArrayList<Media>>, t: Throwable) {

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