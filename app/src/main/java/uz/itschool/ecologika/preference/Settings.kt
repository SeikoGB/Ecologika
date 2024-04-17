package uz.itschool.ecologika.preference

import android.content.Context
import uz.itschool.ecologika.model.Screens

class Settings(context: Context) {
    private val preferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)

    fun setToken(token: String?) {
        preferences.edit().putString("token", token).apply()
    }
    fun setLanguage(int:Int){
        preferences.edit().putInt("language",int).apply()
    }
    fun setLayout(layout:String){
        preferences.edit().putString("layout",layout).apply()
    }
    fun getLayout()=preferences.getString("layout",Screens.HOME.screen)
    fun getLanguage()=preferences.getInt("language",3)

    fun getToken() = preferences.getString("token", null)

    companion object {
        private lateinit var settings: Settings

        fun getSettings(context: Context): Settings {
            if (!::settings.isInitialized) {
                settings = Settings(context)
            }

            return settings
        }
    }
}