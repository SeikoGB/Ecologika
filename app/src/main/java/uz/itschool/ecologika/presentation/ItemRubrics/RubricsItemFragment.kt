package uz.itschool.ecologika.presentation.ItemRubrics

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import coil.load
import uz.itschool.ecologika.R
import uz.itschool.ecologika.databinding.FragmentHomeBinding
import uz.itschool.ecologika.databinding.FragmentRubricsBinding
import uz.itschool.ecologika.databinding.FragmentRubricsItemBinding
import uz.itschool.ecologika.model.RubricsFull
import uz.itschool.ecologika.model.RubricsMini
import java.io.Serializable


private const val ARG_PARAM1 = "minirubrics"
private const val ARG_PARAM2 = "rubrictype"




class RubricsItemFragment : Fragment() {
    private var _binding: FragmentRubricsItemBinding? =null
    private val binding get() =_binding!!
    private val viewModel:RubricsItemViewModel by viewModels()
    private var param1: RubricsMini? = null
    private var param2: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getSerializable(ARG_PARAM1) as RubricsMini
            param2 = it.getInt(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding= FragmentRubricsItemBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var item: RubricsFull = RubricsFull(1,"","","")
        Log.d("TAggggg", "onViewCreated: "+param2.toString())
        var id = param1!!.id
            when(param2){
                1-> viewModel.getInterestingItem(id)
                2-> viewModel.getProblemItem(id)
                3-> viewModel.getDoYouKnowItem(id)
                4-> viewModel.getEcoHistoryItem(id)
                5-> viewModel.getFolklorItem(id)
            }
        var list=ArrayList<RubricsFull>()
        viewModel.rubric.observe(viewLifecycleOwner){ it->
            if (it != null) {
                 item = it[0]
                Log.d("TAggg", "onViewCreated: "+item.toString())
            }
        }
        Log.d("TAggg", "onViewCreatedgg: "+item.content.toString())
        object: CountDownTimer(2000,100){
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                binding.rubricsContentWebView.loadDataWithBaseURL(null, item.content , "text/html", "utf-8", null)
                binding.rubricTitle.text=item.title
                if (item.photo.isNullOrEmpty()){
                    var layoutParams = binding.rubricCard.layoutParams as ConstraintLayout.LayoutParams
                    layoutParams.setMargins(0,300,0,0)
                    binding.rubricCard.layoutParams=layoutParams
                }else{
                    Log.d("TAggg", "onViewCreatedgg: "+item.photo.toString())
                    binding.rubricsImageMore.load("http://ecoedu.uz/pictures/"+item.photo)
                }
            }
        }.start()

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: RubricsMini, param2:Int) =
            RubricsItemFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                    putInt(ARG_PARAM2,param2)
                }
            }
    }
}