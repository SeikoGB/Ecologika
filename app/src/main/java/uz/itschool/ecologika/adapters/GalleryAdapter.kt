package uz.itschool.ecologika.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.internal.findRootView
import coil.load
import uz.itschool.ecologika.R
import uz.itschool.ecologika.model.Photo

class GalleryAdapter(var list:ArrayList<Photo>):RecyclerView.Adapter<GalleryAdapter.MyHolder>() {
    class MyHolder(view: View):RecyclerView.ViewHolder(view){
        var image = view.findViewById<ImageView>(R.id.gallery_photo)
        var commment = view.findViewById<TextView>(R.id.gallery_comment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(LayoutInflater.from(parent.context).inflate(R.layout.gallery_item,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        var item = list[position]
        holder.image.load("http://ecoedu.uz/pictures/"+item.photo)
        var comment=""
        if (!item.comment.isNullOrBlank()){
            comment+=item.comment
        }
        if (!item.author.isNullOrBlank()){
            comment+=" Fotograf : ${item.author}"
        }

        holder.commment.text=comment
    }
}