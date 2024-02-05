package uz.itschool.ecologika.presentation.AboutReserves

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.itschool.ecologika.databinding.FragmentAboutReservesBinding
import uz.itschool.ecologika.model.RubricsFull

private const val ARG_PARAM1 = "param1"



class AboutItemFragment : Fragment() {

    private var param1: RubricsFull? = null
    private var _binding: FragmentAboutReservesBinding? =null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getSerializable(ARG_PARAM1) as RubricsFull?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentAboutReservesBinding.inflate(inflater,container,false)
        var content=param1!!.content
        binding.apply {
            detailWebView.loadDataWithBaseURL(null, content, "text/html", "utf-8", null)
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(problem: RubricsFull) =
            AboutItemFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, problem)
                }
            }
    }
}