package com.song.lottery.base

import android.view.View
import com.song.lottery.R

class TypeFactory {

    companion object {
        const val TYPE_NORMAL = R.layout.item_union_history_title
        const val TYPE_CONTENT = R.layout.item_union_history_content
    }

    fun type(normal: Normal): Int {
        return TYPE_NORMAL
    }

    fun type(normal: GenerateUnionModel): Int {
        return TYPE_CONTENT
    }

    fun createViewHolder(type: Int, itemView: View): BaseViewHolder {
        return when (type) {
            TYPE_NORMAL -> NormalViewHolder(itemView)
            else -> GenerateUnionViewHolder(itemView)
        }
    }

}