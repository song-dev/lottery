package com.song.lottery.base

interface Visitable {
    fun type(typeFactory: TypeFactory): Int
}