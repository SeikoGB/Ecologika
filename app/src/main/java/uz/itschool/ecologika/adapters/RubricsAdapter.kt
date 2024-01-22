package uz.itschool.ecologika.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import uz.itschool.ecologika.R
import uz.itschool.ecologika.model.RubricsFull
import uz.itschool.ecologika.model.RubricsMini

class RubricsAdapter(var list:ArrayList<RubricsMini>,var clickable: RubricsAdapter.click,var type:Int):RecyclerView.Adapter<RubricsAdapter.MyHolder>(){
    class MyHolder(view: View):RecyclerView.ViewHolder(view){
        var title=view.findViewById<TextView>(R.id.rubrics_title)
        var annotation_rubrics=view.findViewById<TextView>(R.id.rubrics_annotation)
        var saw=view.findViewById<TextView>(R.id.rubrics_saw)
        var  card=view.findViewById<CardView>(R.id.rubrics_card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(LayoutInflater.from(parent.context).inflate(R.layout.rubrics_item,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        var item=list[position]
        var tipe = type
        holder.title.text=item.title
        holder.annotation_rubrics.text=item.annotation
        holder.saw.text=item.id.toString()
        holder.card.setOnClickListener {
            clickable.onClick(item)
            Log.d("TAG", "onBindViewHolder: ")
        }
    }
    interface click{
        fun onClick(rubricsMini: RubricsMini)
    }
}