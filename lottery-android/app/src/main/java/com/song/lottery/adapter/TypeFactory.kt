package com.song.lottery.adapter

import android.view.View
import com.song.lottery.R
import com.song.lottery.ui.union.model.GenerateUnionViewHolder
import com.song.lottery.ui.union.model.HistoryContentViewHolder
import com.song.lottery.ui.union.model.HistoryTitleViewHolder

class TypeFactory {

    companion object {
        const val TYPE_HISTORY_TITLE = R.layout.item_union_history_title
        const val TYPE_HISTORY_CONTENT = R.layout.item_union_history_content
        const val TYPE_GENERATE_CONTENT = R.layout.item_union_generate_content
    }

    fun createViewHolder(type: Int, itemView: View): BaseViewHolder {
        return when (type) {
            TYPE_HISTORY_TITLE -> HistoryTitleViewHolder(itemView)
            TYPE_HISTORY_CONTENT -> HistoryContentViewHolder(itemView)
            TYPE_GENERATE_CONTENT -> GenerateUnionViewHolder(itemView)
            else -> GenerateUnionViewHolder(itemView)
        }
    }

}