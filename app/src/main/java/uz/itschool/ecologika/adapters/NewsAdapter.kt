package uz.itschool.ecologika.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.material.imageview.ShapeableImageView
import uz.itschool.ecologika.R
import uz.itschool.ecologika.model.News

class NewsAdapter(var list:ArrayList<News>,var clickable:NewsAdapter.onClick):RecyclerView.Adapter<NewsAdapter.MyHolder>() {
    class MyHolder(view: View):RecyclerView.ViewHolder(view){
        var title=view.findViewById<TextView>(R.id.news_title)
        var card= view.findViewById<CardView>(R.id.news_card)
        var image=view.findViewById<ShapeableImageView>(R.id.news_image)
        var date=view.findViewById<TextView>(R.id.news_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(LayoutInflater.from(parent.context).inflate(R.layout.news_item,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        var item=list[position]
        holder.date.text=item.sdate
        holder.title.text=item.title
        holder.image.load("http://ecoedu.uz/pictures/"+item.photo)
        holder.card.setOnClickListener {
            clickable.click(item)
        }
    }
    interface onClick{
        fun click(news:News)
    }
}