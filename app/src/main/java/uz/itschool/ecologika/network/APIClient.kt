package uz.itschool.ecologika.network

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.itschool.ecologika.preference.Settings

object APIClient {
    private const val baseUrl = "https://api.ecoedu.uz"
    fun getInstance(context: Context): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient(context))
            .build()
    }

    private fun getOkHttpClient(context: Context): OkHttpClient {

        return OkHttpClient.Builder().addInterceptor { chain ->
            val token = Settings.getSettings(context).getToken()
            var request = chain.request()

            if (token != null) {
                request = request.newBuilder().addHeader(
                    "Authorization",
                    "Bearer $token"
                ).addHeader("Content-Type","application/json").build()
            }
            chain.proceed(request)
        }
            .addInterceptor(ChuckerInterceptor(context))
            .build()
    }
}