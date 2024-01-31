package uz.itschool.ecologika.presentation.AboutMedia

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import uz.itschool.ecologika.App
import uz.itschool.ecologika.model.Actions
import uz.itschool.ecologika.model.ByIdRequest
import uz.itschool.ecologika.model.Media
import uz.itschool.ecologika.model.Photo
import uz.itschool.ecologika.model.Request
import uz.itschool.ecologika.model.data
import uz.itschool.ecologika.network.APIClient
import uz.itschool.ecologika.network.APIServise
import uz.itschool.ecologika.preference.Settings

class AboutMediaViewModel : ViewModel(){
    val galleryLiveData= MutableLiveData<ArrayList<Photo>?>()
    private val appAPI: APIServise by lazy { APIClient.getInstance(App.app).create() }
    private val settings: Settings by lazy { Settings.getSettings(App.app) }

    fun getGallery( id:Int){
        var data=data(id.toString())
        var request= ByIdRequest("site",Actions.MEDIA_GALLERY.action,settings.getLanguage(),data)
        appAPI.getPhotogallery(request).enqueue(object :Callback<ArrayList<Photo>>{
            override fun onResponse(
                call: Call<ArrayList<Photo>>,
                response: Response<ArrayList<Photo>>,
            ) {
                var body=response.body()
                if (response.isSuccessful && body!=null){
                    galleryLiveData.value = body
                }
            }

            override fun onFailure(call: Call<ArrayList<Photo>>, t: Throwable) {

            }
        })
    }

}