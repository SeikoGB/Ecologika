package uz.itschool.ecologika.presentation.SplashFragment

import android.media.Image.Plane
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import uz.itschool.ecologika.R
import uz.itschool.ecologika.databinding.FragmentSplashScreenBinding
import uz.itschool.ecologika.presentation.PlaceHolderFragment
import uz.itschool.ecologika.presentation.home_fragment.HomeFragment

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SplashScreenFragment : Fragment() {
    private var _binding: FragmentSplashScreenBinding? =null
    private val viewModel : SplashViewModel by viewModels()
    private val binding get() =_binding!!
    private var param1: String? = null
    private var param2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding=FragmentSplashScreenBinding.inflate(inflater,container,false)
        viewModel.auth()
        viewModel.registerliveData.observe(viewLifecycleOwner){isRegistered->
            if (!isRegistered){
                Toast.makeText(context,"Token was received",Toast.LENGTH_SHORT)
                return@observe
            }
            Handler(Looper.getMainLooper()).postDelayed({
                parentFragmentManager.beginTransaction().replace(R.id.main,PlaceHolderFragment()).commit()
            }, 2000)
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SplashScreenFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}