package uz.itschool.ecologika.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.material.imageview.ShapeableImageView
import uz.itschool.ecologika.R
import uz.itschool.ecologika.model.Media

class MediaAdapter(var list:ArrayList<Media>,var clicksble:MediaAdapter.click ): RecyclerView.Adapter<MediaAdapter.MyHolder>() {
    class MyHolder (var view: View):RecyclerView.ViewHolder(view){
        var title=view.findViewById<TextView>(R.id.photogalerry_title)
        var photo=view.findViewById<ShapeableImageView>(R.id.photogallery_image)
        var card=view.findViewById<CardView>(R.id.photogallery_card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(LayoutInflater.from(parent.context).inflate(R.layout.photogalerry_item,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        var item = list[position]
        holder.title.text=item.name
        holder.photo.load("http://ecoedu.uz/pictures/"+item.photo)
        holder.card.setOnClickListener {
            clicksble.onClick(id = item.id)
        }
    }
    interface click{
        fun onClick(id:Int)
    }
}