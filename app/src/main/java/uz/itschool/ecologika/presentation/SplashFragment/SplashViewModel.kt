package uz.itschool.ecologika.presentation.SplashFragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import uz.itschool.ecologika.App
import uz.itschool.ecologika.model.Auth
import uz.itschool.ecologika.model.AuthResponse
import uz.itschool.ecologika.network.APIClient
import uz.itschool.ecologika.network.APIServise
import uz.itschool.ecologika.preference.Settings

class SplashViewModel():ViewModel() {
    private val appAPI:APIServise by lazy { APIClient.getInstance(App.app).create() }
    private val settings:Settings by lazy { Settings.getSettings(App.app) }
    val registerliveData= MutableLiveData<Boolean>()
    fun auth() = viewModelScope.launch{
        appAPI.getToken(Auth("asda","adbf5a778175ee757c34d0eba4e932bc")).enqueue(object :Callback<AuthResponse>{
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                try {
                    val body = response.body()
                    if (response.isSuccessful && body!=null){
                        val token=response.body()!!.jwt
                        settings.setToken(token)
                        Log.d("sdfsdfsdf", "onResponse: $token")
                        registerliveData.value=true
                    }else{
                        registerliveData.value=false
                    }
                }
                catch (e:Exception){
                    Log.d("sdfsdfsdf", "onResponse: ${e.message}")

                }


            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                registerliveData.value=false
                Log.d("sdfsdfsdf", "onResponse: ${t.message}")

            }
        })
    }
}