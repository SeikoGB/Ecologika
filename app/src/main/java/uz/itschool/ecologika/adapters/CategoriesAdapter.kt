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

class CategoriesAdapter(var list:ArrayList<Media>,var clickable:CategoriesAdapter.Click): RecyclerView.Adapter<CategoriesAdapter.MyHolder>() {
    class MyHolder(view: View):RecyclerView.ViewHolder(view) {
        var image=view.findViewById<ImageView>(R.id.category_img)
        var title=view.findViewById<TextView>(R.id.category_name)
        var card=view.findViewById<CardView>(R.id.category_card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(LayoutInflater.from(parent.context).inflate(R.layout.category_item,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        var item = list[position]
        holder.title.text=item.name
        holder.image.load("http://ecoedu.uz/pictures/"+item.photo)
        holder.card.setOnClickListener {
            clickable.click(id = item.id)
        }
    }
    interface Click{
        fun click(id:Int)
    }
}