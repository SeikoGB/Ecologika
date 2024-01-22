package uz.itschool.ecologika.presentation.Quotes

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import uz.itschool.ecologika.R
import uz.itschool.ecologika.adapters.QuoteAdapter
import uz.itschool.ecologika.databinding.FragmentHomeBinding
import uz.itschool.ecologika.databinding.FragmentQuotesBinding
import uz.itschool.ecologika.model.Quote

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class QuotesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentQuotesBinding? =null
    private val binding get() =_binding!!
    private var quotes=ArrayList<Quote>()
    private val viewModel:QuoteViewModel by viewModels()
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
        _binding=FragmentQuotesBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var adapter=QuoteAdapter(quotes)
        viewModel.getQuotes()
        viewModel.quotesLiveData.observe(viewLifecycleOwner){items->
            if (items!=null){
                quotes.addAll(items)
            }
            binding.quoteRv.adapter=adapter
            object: CountDownTimer(500,100){
                override fun onTick(millisUntilFinished: Long) {

                }

                override fun onFinish() {
                    adapter.notifyDataSetChanged()
                }
            }.start()

        }


    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            QuotesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}