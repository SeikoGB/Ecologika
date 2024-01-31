
package uz.itschool.ecologika.presentation.media

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import uz.itschool.ecologika.R
import uz.itschool.ecologika.adapters.MediaAdapter
import uz.itschool.ecologika.databinding.FragmentMediaBinding
import uz.itschool.ecologika.model.Media


class MediaFragment : Fragment() {
    private var _binding: FragmentMediaBinding? =null
    private val binding get() =_binding!!
    var medias:ArrayList<Media> =ArrayList<Media>()
    val viewModel:MediaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding= FragmentMediaBinding.inflate(inflater,container,false)
        viewModel.getMedia()
        var mediaAdapter= MediaAdapter(medias,object :MediaAdapter.click{
            override fun onClick(id: Int) {
                var bundle=Bundle()
                bundle.putInt("photo_id",id)
                findNavController().navigate(R.id.action_mediasFragment_to_aboutMediaFragment,bundle)
            }
        })
        viewModel.mediaLiveData.observe(viewLifecycleOwner){it->
            if (it != null) {
                medias.addAll(it)
            }
        }
        object: CountDownTimer(500,100){
            override fun onTick(millisUntilFinished: Long) {
            }
            override fun onFinish() {
                binding.mediaRv.adapter=mediaAdapter
                mediaAdapter.notifyDataSetChanged()
            }
        }.start()
        return binding.root
    }


}