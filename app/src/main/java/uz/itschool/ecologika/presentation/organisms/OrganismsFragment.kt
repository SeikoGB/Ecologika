package uz.itschool.ecologika.presentation.organisms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import uz.itschool.ecologika.R
import uz.itschool.ecologika.databinding.FragmentOrganismsBinding
import uz.itschool.ecologika.databinding.FragmentRubricsBinding
import uz.itschool.ecologika.model.Organism
import uz.itschool.ecologika.model.RubricsMini
import uz.itschool.ecologika.presentation.Rubrics.RubricsViewModel


private const val ARG_PARAM1 = "id"

class OrganismsFragment : Fragment() {
    private var param1: Int? = null
    private var _binding: FragmentOrganismsBinding? =null

    private val binding get() =_binding!!
    private val viewModel : OrganismsViewModel by viewModels()
    private var birds=ArrayList<Organism>()
    private var mammals=ArrayList<Organism>()
    private var reptile=ArrayList<Organism>()
    private var fishes=ArrayList<Organism>()
    private var bugs=ArrayList<Organism>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        return inflater.inflate(R.layout.fragment_organisms, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1:Int) =
            OrganismsFragment().apply {1
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                }
            }
    }
}