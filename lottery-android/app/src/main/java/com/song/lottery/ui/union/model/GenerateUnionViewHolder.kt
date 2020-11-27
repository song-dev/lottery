package com.song.lottery.ui.union.model

import android.view.View
import android.widget.TextView
import com.song.lottery.R
import com.song.lottery.adapter.BaseViewHolder
import com.song.lottery.adapter.LotteryAdapter

class GenerateUnionViewHolder(itemView: View): BaseViewHolder(itemView) {
    override fun <T> setUpView(model: T, position: Int, adapter: LotteryAdapter) {
        val textView = itemView.findViewById<TextView>(R.id.tv_content)
        val normal = model as GenerateUnionModel
        textView.text = normal.text
    }
}