package uz.itschool.ecologika.presentation.AboutItem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.itschool.ecologika.databinding.FragmentAvoutItemBinding
import uz.itschool.ecologika.model.ProblemFull

private const val ARG_PARAM1 = "param1"



class AboutItemFragment : Fragment() {

    private var param1: ProblemFull? = null
    private var _binding: FragmentAvoutItemBinding? =null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getSerializable(ARG_PARAM1) as ProblemFull?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentAvoutItemBinding.inflate(inflater,container,false)
        var content=param1!!.content
        binding.apply {
            detailWebView.loadDataWithBaseURL(null, content, "text/html", "utf-8", null)
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(problem: ProblemFull) =
            AboutItemFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, problem)
                }
            }
    }
}