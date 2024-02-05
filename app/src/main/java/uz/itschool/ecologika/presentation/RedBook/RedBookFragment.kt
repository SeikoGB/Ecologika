package uz.itschool.ecologika.presentation.RedBook

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import uz.itschool.ecologika.R
import uz.itschool.ecologika.databinding.FragmentQuotesBinding
import uz.itschool.ecologika.databinding.FragmentRedBookBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RedBookFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RedBookFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentRedBookBinding? =null
    private val binding get() =_binding!!

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
        _binding= FragmentRedBookBinding.inflate(inflater,container,false)
        binding.apply {
            organismCard.setOnClickListener {
                findNavController().navigate(R.id.action_redBookFragment_to_organismsFragment)
            }
            plantCard.setOnClickListener {
                findNavController().navigate(R.id.action_redBookFragment_to_plantsFragment)
            }
            reservesCard.setOnClickListener {
                findNavController().navigate(R.id.action_redBookFragment_to_reservesFragment)
            }
        }
        return binding.root
    }

    companion object {


        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RedBookFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}