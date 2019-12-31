package com.infernal93.listwithsearchmvvm.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.infernal93.listwithsearchmvvm.R
import com.infernal93.listwithsearchmvvm.entity.Category
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

/**
 * Created by Armen Mkhitaryan on 30.12.2019.
 */
class CategoryAdapter(private val context: Context, private val mCategoryList: ArrayList<Category> = ArrayList())
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mSourceList: ArrayList<Category> = ArrayList(mCategoryList)

    fun setupCategory(categoryList: ArrayList<Category>) {
        mSourceList.clear()
        mSourceList.addAll(categoryList)
        search(query = "")
    }

    fun search(query: String) {
        mCategoryList.clear()
        mSourceList.forEach {
            if (it.name.contains(query, ignoreCase = true)) {
                mCategoryList.add(it)
            }
        }
        notifyDataSetChanged()
    }

    fun sortByName() {
        mCategoryList.sortBy { it.name }
        notifyDataSetChanged()
    }

    fun sortByPrice() {
        mCategoryList.sortBy { it.price }
        notifyDataSetChanged()
    }

    fun filter() {

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CategoryViewHolder) {
            holder.bind(categoryModel = mCategoryList[position])

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.cell_category, parent, false)
        return CategoryViewHolder(itemView = itemView)
    }

    override fun getItemCount(): Int {
        return mCategoryList.count()
    }

    class CategoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var mCategoryIcon: CircleImageView = itemView.findViewById(R.id.category_icon)
        var mCategoryName: TextView = itemView.findViewById(R.id.category_name)
        var mCategoryPrice: TextView = itemView.findViewById(R.id.category_price)
        private var mCategoryType: TextView = itemView.findViewById(R.id.category_type)

        fun bind(categoryModel: Category) {
            categoryModel.icon.let { url ->
                Picasso.with(itemView.context).load(url)
                    .into(mCategoryIcon)
            }

            mCategoryName.text = categoryModel.name
            mCategoryPrice.text = categoryModel.price.toString()
            mCategoryType.text = categoryModel.category

        }
    }
}