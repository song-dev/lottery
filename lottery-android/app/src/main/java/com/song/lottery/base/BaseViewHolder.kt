package com.song.lottery.base

import android.util.SparseArray
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder(private val mItemView: View) : RecyclerView.ViewHolder(mItemView) {

    private val views: SparseArray<View> = SparseArray()

    fun getView(resID: Int): View {
        var view = views[resID]
        if (view == null) {
            view = mItemView.findViewById(resID)
            views.put(resID, view)
        }
        return view
    }

    abstract fun <T> setUpView(model: T, position: Int, adapter: MultiTypeAdapter)

}