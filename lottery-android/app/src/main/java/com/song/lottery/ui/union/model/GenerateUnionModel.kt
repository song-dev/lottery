package com.song.lottery.ui.union.model

import com.song.lottery.adapter.Model
import com.song.lottery.adapter.TypeFactory

class GenerateUnionModel(var text: String) : Model {

    override fun type(): Int {
        return TypeFactory.TYPE_GENERATE_CONTENT
    }
}