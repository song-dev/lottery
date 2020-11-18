package com.song.lottery.base

import android.view.View
import android.widget.TextView
import com.song.lottery.R

class NormalViewHolder<Normal>(itemView: View) : BaseViewHolder(itemView) {
    override fun <Normal> setUpView(model: Normal, position: Int, adapter: MultiTypeAdapter) {
//        val textView = getView(R.id.normal_title) as TextView
//        textView.text = model.text
//        textView.setOnClickListener {
//            Toast.makeText(textView.context, model.text, Toast.LENGTH_SHORT).show()
//        }
        val textView = itemView.findViewById<TextView>(R.id.textView)
        textView.text = model.toString()
    }
}