package uz.itschool.ecologika.presentation.AboutNews

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import coil.load
import uz.itschool.ecologika.R
import uz.itschool.ecologika.databinding.FragmentAboutNewsBinding
import uz.itschool.ecologika.databinding.FragmentNewsBinding
import uz.itschool.ecologika.model.News
import uz.itschool.ecologika.presentation.news.NewsViewModel

private const val ARG_PARAM1 = "param1"


class AboutNewsFragment : Fragment() {
    private var param1: News? = null

    private var _binding: FragmentAboutNewsBinding? =null
    private val binding get() =_binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getSerializable(ARG_PARAM1) as News
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding= FragmentAboutNewsBinding.inflate(inflater,container,false)
        var item= param1!!
        binding.newsImageMore.load("http://ecoedu.uz/pictures/"+item.photo)
        binding.newsContentWebView.loadDataWithBaseURL(null, item.annotation, "text/html", "utf-8", null)
        binding.newsTitle.text=item.title
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: News) =
            AboutNewsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                }
            }
    }
}