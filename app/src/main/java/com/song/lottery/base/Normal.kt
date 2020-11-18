package com.song.lottery.base

class Normal(var text: String) : Visitable {

    override fun type(typeFactory: TypeFactory): Int {
        return typeFactory.type(this)
    }

}