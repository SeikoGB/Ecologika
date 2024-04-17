package uz.itschool.ecologika.presentation.home_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import uz.itschool.ecologika.R
import uz.itschool.ecologika.adapters.ProblemAdapter
import uz.itschool.ecologika.databinding.FragmentHomeBinding
import uz.itschool.ecologika.model.RubricsFull

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? =null
    private val binding get() =_binding!!
    private val viewModel:HomeViewModel by viewModels()
    var problemss:ArrayList<RubricsFull> =ArrayList<RubricsFull>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.problemsLiveData.observe(viewLifecycleOwner){list->
        if (list!= null){
            problemss.addAll(list)
        }
        }
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
}