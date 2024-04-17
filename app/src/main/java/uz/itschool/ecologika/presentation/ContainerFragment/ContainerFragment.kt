package uz.itschool.ecologika.presentation.ContainerFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import uz.itschool.ecologika.MainActivity
import uz.itschool.ecologika.R
import uz.itschool.ecologika.databinding.FragmentContainerBinding
import uz.itschool.ecologika.databinding.FragmentMediaBinding


class ContainerFragment : Fragment() {
    private var _binding: FragmentContainerBinding? =null
    private val binding get() =_binding!!
    lateinit var toogle:ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding=FragmentContainerBinding.inflate(inflater,container,false)

        toogle= ActionBarDrawerToggle(MainActivity(),binding.drawerView,R.string.open,R.string.close)

        binding.drawerView.addDrawerListener(toogle)
        toogle.syncState()


        return binding.root
    }
}