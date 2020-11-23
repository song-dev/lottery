package com.song.lottery.base

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class LotteryAdapter(private var models: List<Model>) :
    RecyclerView.Adapter<BaseViewHolder>() {

    private val typeFactory: TypeFactory = TypeFactory()

    fun updateData(list: List<Model>) {
        models = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val itemView = View.inflate(parent.context, viewType, null)
        return typeFactory.createViewHolder(viewType, itemView)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.setUpView(models[position], position, this)
    }

    override fun getItemCount(): Int {
        return models.size
    }

    override fun getItemViewType(position: Int): Int {
        return models[position].type()
    }

}