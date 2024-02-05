package uz.itschool.ecologika.presentation.home_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import uz.itschool.ecologika.R
import uz.itschool.ecologika.adapters.ProblemAdapter
import uz.itschool.ecologika.databinding.FragmentHomeBinding
import uz.itschool.ecologika.model.RubricsFull

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? =null
    private val binding get() =_binding!!
    var problemss:ArrayList<RubricsFull> =ArrayList<RubricsFull>()

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
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var problemsAdapter=ProblemAdapter(problemss,object :ProblemAdapter.clickable{
            override fun click(problemFull: RubricsFull) {

            }

        },binding.homePager)

        binding.apply {
            newsBtn.setOnClickListener{
                findNavController().navigate(R.id.action_homeFragment_to_newsFragment)
            }
            quotes.setOnClickListener{
                findNavController().navigate(R.id.action_homeFragment_to_quotesFragment)
            }
            rubricsBtn.setOnClickListener{
                findNavController().navigate(R.id.action_homeFragment_to_rubricsFragment)
            }
            fotoBtn.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_mediasFragment)
            }
            redBook.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_redBookFragment)
            }

        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}