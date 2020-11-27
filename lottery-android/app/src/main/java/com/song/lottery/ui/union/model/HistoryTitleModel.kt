package com.song.lottery.ui.union.model

import com.song.lottery.adapter.Model
import com.song.lottery.adapter.TypeFactory

class HistoryTitleModel(val icon: Int, val title: String, val tip: List<String>) : Model {

    override fun type(): Int {
        return TypeFactory.TYPE_HISTORY_TITLE
    }

}