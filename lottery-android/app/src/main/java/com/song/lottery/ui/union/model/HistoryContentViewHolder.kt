package com.song.lottery.ui.union.model

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import com.song.lottery.R
import com.song.lottery.adapter.BaseViewHolder
import com.song.lottery.adapter.LotteryAdapter

class HistoryContentViewHolder(itemView: View) : BaseViewHolder(itemView) {
    override fun <T> setUpView(model: T, position: Int, adapter: LotteryAdapter) {
        model as HistoryContentModel
        val ball1 = getView(R.id.tv_ball1) as AppCompatTextView
        ball1.text = model.list[1]
        val ball2 = getView(R.id.tv_ball2) as AppCompatTextView
        ball2.text = model.list[2]
        val ball3 = getView(R.id.tv_ball3) as AppCompatTextView
        ball3.text = model.list[3]
        val ball4 = getView(R.id.tv_ball4) as AppCompatTextView
        ball4.text = model.list[4]
        val ball5 = getView(R.id.tv_ball5) as AppCompatTextView
        ball5.text = model.list[5]
        val ball6 = getView(R.id.tv_ball6) as AppCompatTextView
        ball6.text = model.list[6]
        val ball7 = getView(R.id.tv_ball7) as AppCompatTextView
        ball7.text = model.list[7]
    }
}