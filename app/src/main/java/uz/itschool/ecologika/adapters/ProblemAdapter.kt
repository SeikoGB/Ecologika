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
import uz.itschool.ecologika.model.ProblemFull

class ProblemAdapter(var list:ArrayList<ProblemFull>,var onClick:ProblemAdapter.clickable): RecyclerView.Adapter<ProblemAdapter.MyHolder>() {
    class MyHolder(var view: View):RecyclerView.ViewHolder(view){
        var image=view.findViewById<ShapeableImageView>(R.id.problem_image)
        var title=view.findViewById<TextView>(R.id.problem_title)
        var card=view.findViewById<CardView>(R.id.problem_card)
    }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
         return MyHolder(LayoutInflater.from(parent.context).inflate(R.layout.home_item,parent,false))
     }

     override fun getItemCount(): Int {
         return list.size
     }

     override fun onBindViewHolder(holder: MyHolder, position: Int) {
         var item=list[position]
         holder.image.load("http://ecoedu.uz/slider/pictures/"+item.photo)
         holder.title.text=item.title
         holder.card.setOnClickListener {
             onClick.click(item )
         }
     }
    interface clickable{
        fun click(problemFull: ProblemFull)
    }
 }