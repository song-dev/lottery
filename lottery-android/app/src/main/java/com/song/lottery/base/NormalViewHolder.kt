package com.song.lottery.base

import android.view.View
import android.widget.TextView
import com.song.lottery.R

class NormalViewHolder(itemView: View) : BaseViewHolder(itemView) {
    override fun <T> setUpView(model: T, position: Int, adapter: MultiTypeAdapter) {
//        val textView = getView(R.id.normal_title) as TextView
//        textView.text = model.text
//        textView.setOnClickListener {
//            Toast.makeText(textView.context, model.text, Toast.LENGTH_SHORT).show()
//        }
        val textView = itemView.findViewById<TextView>(R.id.textView)
        val normal = model as Normal
        textView.text = normal.text
    }
}