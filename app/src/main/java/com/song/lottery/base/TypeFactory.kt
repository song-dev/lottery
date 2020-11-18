package com.song.lottery.base

import android.view.View

class TypeFactory {

    companion object {
        const val TYPE_NORMAL: Int = com.song.lottery.R.layout.item_union_history_title
    }

    fun type(normal: Normal): Int {
        return TYPE_NORMAL
    }

    fun createViewHolder(type: Int, itemView: View): BaseViewHolder {
        if (TYPE_NORMAL == type) {
        }
        return NormalViewHolder<Normal>(itemView)
    }

}