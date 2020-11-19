package com.song.lottery.base

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MultiTypeAdapter(private var models: List<Visitable>) :
    RecyclerView.Adapter<BaseViewHolder>() {

    private val typeFactory: TypeFactory = TypeFactory()

    fun updateData(list: List<Visitable>) {
        models = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val context = parent.context
        val itemView = View.inflate(context, viewType, null)
        return typeFactory.createViewHolder(viewType, itemView)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.setUpView(models[position], position, this)
    }

    override fun getItemCount(): Int {
        return models.size
    }

    override fun getItemViewType(position: Int): Int {
        return models[position].type(typeFactory)
    }

}