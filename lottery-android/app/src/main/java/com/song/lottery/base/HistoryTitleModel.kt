package com.song.lottery.base

class HistoryTitleModel(val icon: Int, val title: String, val tip: List<String>) : Model {

    override fun type(): Int {
        return TypeFactory.TYPE_HISTORY_TITLE
    }

}