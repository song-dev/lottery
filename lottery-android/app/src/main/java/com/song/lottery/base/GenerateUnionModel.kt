package com.song.lottery.base

class GenerateUnionModel(var text: String) :Model{

    override fun type(): Int {
        return TypeFactory.TYPE_HISTORY_CONTENT
    }
}