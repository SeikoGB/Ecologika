package uz.itschool.ecologika.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter

class ExpandableListAdapter internal constructor(
    private val context:Context,var titleList:ArrayList<String>,var dataList:HashMap<String,ArrayList<String>>
): BaseExpandableListAdapter() {
    override fun getGroupCount(): Int {
        TODO("Not yet implemented")
    }

    override fun getChildrenCount(p0: Int): Int {
        TODO("Not yet implemented")
    }

    override fun getGroup(p0: Int): Any {
        TODO("Not yet implemented")
    }

    override fun getChild(p0: Int, p1: Int): Any {
        return p1.toLong()
    }

    override fun getGroupId(p0: Int): Long {
        TODO("Not yet implemented")
    }

    override fun getChildId(p0: Int, p1: Int): Long {
        TODO("Not yet implemented")
    }

    override fun hasStableIds(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getGroupView(p0: Int, p1: Boolean, p2: View?, p3: ViewGroup?): View {
        TODO("Not yet implemented")
    }

    override fun getChildView(p0: Int, p1: Int, p2: Boolean, p3: View?, p4: ViewGroup?): View {
        TODO("Not yet implemented")
    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean {
        return true
    }
}