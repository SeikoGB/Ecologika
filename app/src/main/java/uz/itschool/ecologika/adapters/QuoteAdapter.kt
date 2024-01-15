package uz.itschool.ecologika.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import uz.itschool.ecologika.R
import uz.itschool.ecologika.model.Quote

class QuoteAdapter(var list:ArrayList<Quote>):RecyclerView.Adapter<QuoteAdapter.MyHolder>() {
    class MyHolder(var view: View):RecyclerView.ViewHolder(view) {
        var author=view.findViewById<TextView>(R.id.quote_author)
        var content=view.findViewById<TextView>(R.id.quote_content)
        var author_degree=view.findViewById<TextView>(R.id.quote_author_deegree)
        var image=view.findViewById<ImageView>(R.id.quote_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(LayoutInflater.from(parent.context).inflate(R.layout.quotes_item,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        var item=list[position]
        holder.content.text=item.quote
        holder.author.text=item.name
        holder.author_degree.text=item.degree
        holder.image.load("http://ecoedu.uz/pictures/"+item.photo)
    }
}