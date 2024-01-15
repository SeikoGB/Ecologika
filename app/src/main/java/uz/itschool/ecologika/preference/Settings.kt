package uz.itschool.ecologika.preference

import android.content.Context

class Settings(context: Context) {
    private val preferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)

    fun setToken(token: String?) {
        preferences.edit().putString("token", token).apply()
    }
    fun setLanguage(int:Int){
        preferences.edit().putInt("language",int).apply()
    }
    fun getLanguage()=preferences.getInt("language",1)

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