package uz.itschool.ecologika.presentation.Reserves

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import uz.itschool.ecologika.R
import uz.itschool.ecologika.databinding.FragmentQuotesBinding
import uz.itschool.ecologika.databinding.FragmentReservesBinding
import uz.itschool.ecologika.model.Quote
import uz.itschool.ecologika.presentation.Quotes.QuoteViewModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ReservesFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentReservesBinding? =null
    private val binding get() =_binding!!
    private var quotes=ArrayList<Quote>()
    private val viewModel: QuoteViewModel by viewModels()

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

        return inflater.inflate(R.layout.fragment_reserves, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ReservesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}