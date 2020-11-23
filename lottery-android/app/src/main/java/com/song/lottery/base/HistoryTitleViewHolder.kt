package com.song.lottery.base

import android.text.Html
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import com.google.android.material.imageview.ShapeableImageView
import com.song.lottery.R
import java.math.BigDecimal
import java.math.RoundingMode

class HistoryTitleViewHolder(itemView: View) : BaseViewHolder(itemView) {
    override fun <T> setUpView(model: T, position: Int, adapter: LotteryAdapter) {
        val icon = getView(R.id.icon) as ShapeableImageView
        val title = getView(R.id.tv_title) as AppCompatTextView
        val tip = getView(R.id.tv_title_tip) as AppCompatTextView
        model as HistoryTitleModel
        icon.setBackgroundResource(model.icon)
        title.text = model.title
        tip.text = model.tip.toString()
        println(model.tip.toString())
        val b = BigDecimal((model.tip[8].replace(Regex("\\D"),"").toDouble() / 100000000.0).toString())
        val one = BigDecimal("1")
        val end = b.divide(one, 2, RoundingMode.HALF_UP).toDouble()
        val s =
            "<font color='#928F93'>${model.tip[0]}期  ${model.tip[9]}</font>  <font color='#2D2D2D'>奖池：</font> <font color='#BF4D56'>${end}亿</font>"
        val content = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            Html.fromHtml(s, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(s)
        }
        tip.text = content
    }
}