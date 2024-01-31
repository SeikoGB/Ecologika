package uz.itschool.ecologika.presentation.AboutMedia

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import uz.itschool.ecologika.R
import uz.itschool.ecologika.adapters.GalleryAdapter
import uz.itschool.ecologika.databinding.FragmentAboutMediaBinding
import uz.itschool.ecologika.databinding.FragmentAboutNewsBinding
import uz.itschool.ecologika.model.Photo

private const val ARG_PARAM1 = "photo_id"



class AboutMediaFragment : Fragment() {

    private var param1: Int? = null
    private val viewModel:AboutMediaViewModel by viewModels()
    private var _binding: FragmentAboutMediaBinding? =null
    private val binding get() =_binding!!

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
        _binding= FragmentAboutMediaBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var list = ArrayList<Photo>()
        var adapter=GalleryAdapter(list)
        var item = param1!!
        viewModel.getGallery(item)
        viewModel.galleryLiveData.observe(viewLifecycleOwner){it->
            if (it != null) {
                list.addAll(it)
            }
        }
        object: CountDownTimer(500,100){
            override fun onTick(millisUntilFinished: Long) {
            }
            override fun onFinish() {
                binding.galleryPager.adapter=adapter
                adapter.notifyDataSetChanged()
            }
        }.start()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Int) =
            AboutMediaFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                }
            }
    }
}