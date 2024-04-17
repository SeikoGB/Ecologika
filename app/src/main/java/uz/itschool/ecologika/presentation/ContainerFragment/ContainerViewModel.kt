package uz.itschool.ecologika.presentation.ContainerFragment

import androidx.lifecycle.ViewModel
import uz.itschool.ecologika.App
import uz.itschool.ecologika.preference.Settings

class ContainerViewModel :ViewModel(){
    var inst_layout=Settings(App.app).getLayout()
}