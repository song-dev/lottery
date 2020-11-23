package com.song.lottery.base

import android.util.SparseArray
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val views: SparseArray<View> = SparseArray()

    fun getView(resID: Int): View {
        var view = views[resID]
        if (view == null) {
            view = itemView.findViewById(resID)
            views.put(resID, view)
        }
        return view
    }

    abstract fun <T> setUpView(model: T, position: Int, adapter: LotteryAdapter)

}