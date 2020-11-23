package com.song.lottery.base

import android.view.View
import android.widget.TextView
import com.song.lottery.R

class GenerateUnionViewHolder(itemView: View):BaseViewHolder(itemView) {
    override fun <T> setUpView(model: T, position: Int, adapter: LotteryAdapter) {
        val textView = itemView.findViewById<TextView>(R.id.content)
        val normal = model as GenerateUnionModel
        textView.text = normal.text
    }
}