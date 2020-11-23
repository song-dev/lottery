package com.song.lottery.base

import android.view.View
import com.song.lottery.R

class TypeFactory {

    companion object {
        const val TYPE_HISTORY_TITLE = R.layout.item_union_history_title
        const val TYPE_HISTORY_CONTENT = R.layout.item_union_history_content
    }

    fun createViewHolder(type: Int, itemView: View): BaseViewHolder {
        return when (type) {
            TYPE_HISTORY_TITLE -> HistoryTitleViewHolder(itemView)
            TYPE_HISTORY_CONTENT -> HistoryContentViewHolder(itemView)
            else -> GenerateUnionViewHolder(itemView)
        }
    }

}