package uz.itschool.ecologika.presentation.Rubrics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import uz.itschool.ecologika.R
import uz.itschool.ecologika.adapters.RubricsAdapter
import uz.itschool.ecologika.databinding.FragmentNewsBinding
import uz.itschool.ecologika.databinding.FragmentRubricsBinding
import uz.itschool.ecologika.databinding.FragmentRubricsItemBinding
import uz.itschool.ecologika.model.Actions
import uz.itschool.ecologika.model.RubricsMini
import uz.itschool.ecologika.presentation.ItemRubrics.RubricsItemFragment
import uz.itschool.ecologika.presentation.news.NewsViewModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class RubricsFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentRubricsBinding? =null
    private val binding get() =_binding!!
    private val viewModel : RubricsViewModel by viewModels()
    private var interestings=ArrayList<RubricsMini>()
    private var problems=ArrayList<RubricsMini>()
    private var folklors=ArrayList<RubricsMini>()
    private var eco_histories=ArrayList<RubricsMini>()
    private var doyouknows=ArrayList<RubricsMini>()
    private val Interesting=1
    private val Problems=2
    private val DoYouKnows=3
    private val EcoHistories=4
    private val Folklor=5

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
        _binding=FragmentRubricsBinding.inflate(inflater,container,false)
        BottomSheetBehavior.from(binding.rubricsBottomSheet).apply {
            peekHeight=130
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var interestingsAdapter=RubricsAdapter(interestings,object :RubricsAdapter.click{
            override fun onClick(rubricsMini: RubricsMini) {
                var bundle=Bundle()
                bundle.putSerializable("minirubrics",rubricsMini)
                bundle.putInt("rubrictype",Interesting)
                findNavController().navigate(R.id.action_rubricsFragment_to_rubricsItemFragment,bundle)
            }
        },Interesting)

        var problemsAdapter=RubricsAdapter(problems,object :RubricsAdapter.click{
            override fun onClick(rubricsMini: RubricsMini) {
                var bundle=Bundle()
                bundle.putSerializable("minirubrics",rubricsMini)
                bundle.putInt("rubrictype",Problems)
                findNavController().navigate(R.id.action_rubricsFragment_to_rubricsItemFragment,bundle)
            }
        },Problems)




        var doyouknowAdapter=RubricsAdapter(doyouknows,object :RubricsAdapter.click{
            override fun onClick(rubricsMini: RubricsMini) {
                var bundle=Bundle()
                bundle.putSerializable("minirubrics",rubricsMini)
                bundle.putInt("rubrictype",DoYouKnows)
                findNavController().navigate(R.id.action_rubricsFragment_to_rubricsItemFragment,bundle)
            }


        },DoYouKnows)

        var ecohistoriesAdapter=RubricsAdapter(eco_histories, object :RubricsAdapter.click{
            override fun onClick(rubricsMini: RubricsMini) {
                var bundle=Bundle()
                bundle.putSerializable("minirubrics",rubricsMini)
                bundle.putInt("rubrictype",EcoHistories)
                findNavController().navigate(R.id.action_rubricsFragment_to_rubricsItemFragment,bundle)
            }
        },EcoHistories)


        var folklorAdapter=RubricsAdapter(folklors,object :RubricsAdapter.click{
            override fun onClick(rubricsMini: RubricsMini) {
                var bundle=Bundle()
                bundle.putSerializable("minirubrics",rubricsMini)
                bundle.putInt("rubrictype",Folklor)
                findNavController().navigate(R.id.action_rubricsFragment_to_rubricsItemFragment,bundle)
            }


        },Folklor)


        viewModel.loadInterestings()
        viewModel.interestingsLiveData.observe(viewLifecycleOwner){it->
            if (it != null) {
                interestings.addAll(it)
            }
            interestingsAdapter.notifyDataSetChanged()
        }
        viewModel.loadecoProblems()
        viewModel.problemsLiveData.observe(viewLifecycleOwner){it->
            if (it != null) {
                problems.addAll(it)
            }
            problemsAdapter.notifyDataSetChanged()
        }
        viewModel.loadEcoFolklors()
        viewModel.folklorsLiveData.observe(viewLifecycleOwner){it->
            if (it != null) {
                folklors.addAll(it)
            }
            folklorAdapter.notifyDataSetChanged()
        }
        viewModel.loadDoYouKnows()
        viewModel.doyouknowsLiveData.observe(viewLifecycleOwner){it->
            if (it != null) {
                doyouknows.addAll(it)
            }
            doyouknowAdapter.notifyDataSetChanged()
        }
        viewModel.loadEcoHistories()
        viewModel.ecohistoryLiveData.observe(viewLifecycleOwner){it->
            if (it != null) {
                eco_histories.addAll(it)
            }
            ecohistoriesAdapter.notifyDataSetChanged()
        }
        binding.rubricsRv.adapter=interestingsAdapter

        binding.ecoProblemsBtn.setOnClickListener {
            binding.rubricsRv.adapter=problemsAdapter
        }
        binding.ecoFolklorBtn.setOnClickListener {
            binding.rubricsRv.adapter=folklorAdapter
        }
        binding.interestigsBtn.setOnClickListener {
            binding.rubricsRv.adapter=interestingsAdapter
        }
        binding.doYouKnowBtn.setOnClickListener {
            binding.rubricsRv.adapter=doyouknowAdapter
        }
        binding.ecoHistoryBtn.setOnClickListener {
            binding.rubricsRv.adapter=ecohistoriesAdapter
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RubricsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}