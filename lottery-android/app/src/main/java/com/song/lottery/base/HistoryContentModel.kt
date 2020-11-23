package com.song.lottery.base

class HistoryContentModel(var list: List<String>) : Model {

    override fun type(): Int {
        return TypeFactory.TYPE_HISTORY_CONTENT
    }

}