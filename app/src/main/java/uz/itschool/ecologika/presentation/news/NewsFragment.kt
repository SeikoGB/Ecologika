package uz.itschool.ecologika.presentation.news

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import uz.itschool.ecologika.R
import uz.itschool.ecologika.adapters.NewsAdapter
import uz.itschool.ecologika.databinding.FragmentNewsBinding
import uz.itschool.ecologika.databinding.FragmentSplashScreenBinding
import uz.itschool.ecologika.model.News
import uz.itschool.ecologika.presentation.AboutNews.AboutNewsFragment
import uz.itschool.ecologika.presentation.SplashFragment.SplashViewModel

private const val ARG_PARAM1 = "param1"

private const val ARG_PARAM2 = "param2"
class NewsFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentNewsBinding? =null
    private val viewModel : NewsViewModel by viewModels()
    private val binding get() =_binding!!
    private var news =ArrayList<News>()
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
        _binding=FragmentNewsBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var adapter= NewsAdapter(news, object : NewsAdapter.onClick{
            override fun click(news: News) {
                var bundle = Bundle()
                bundle.putSerializable("param1",news)
                findNavController().navigate(R.id.action_newsFragment_to_aboutNewsFragment,bundle)
            }
        })
        viewModel.getNews()
        viewModel.newsLiveData.observe(viewLifecycleOwner){list->
            if (list != null) {
                news.addAll(list)
            }
        }
        binding.newsRv.adapter=adapter
        object: CountDownTimer(500,100){
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                adapter.notifyDataSetChanged()
            }
        }.start()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NewsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

