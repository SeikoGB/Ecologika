package uz.itschool.ecologika.presentation.category_red_book

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import uz.itschool.ecologika.R
import uz.itschool.ecologika.adapters.CategoriesAdapter
import uz.itschool.ecologika.databinding.FragmentCategoryRedBookBinding
import uz.itschool.ecologika.model.Media
import uz.itschool.ecologika.model.Quote
import uz.itschool.ecologika.presentation.Quotes.QuoteViewModel

private const val ARG_PARAM1 = "section_id"



class CategoryRedBookFragment : Fragment() {
    private var param1: Int? = null

    private var _binding: FragmentCategoryRedBookBinding? =null
    private val binding get() =_binding!!
    private var categories=ArrayList<Media>()
    private val viewModel: RedBookCategoryViewModel by viewModels()

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
        _binding=FragmentCategoryRedBookBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getCategories(param1!!)
        var adapter= CategoriesAdapter(categories,object :CategoriesAdapter.Click{
            override fun click(id: Int) {
                if (param1==15){
                    var bundle=Bundle()
                    bundle.putInt("id",id)
                    findNavController().navigate(R.id.action_categoryRedBookFragment_to_organismsFragment,bundle)
                }else{
                    var bundle=Bundle()
                    bundle.putInt("id",id)
                    findNavController().navigate(R.id.action_categoryRedBookFragment_to_plantsFragment,bundle)
                }
            }
        })
        viewModel.categoriesLiveData.observe(viewLifecycleOwner){items->
            if (items!=null){
                categories.addAll(items)
            }
            binding.categoryId.adapter=adapter
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
        fun newInstance(param1: Int) =
            CategoryRedBookFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                }
            }
    }
}