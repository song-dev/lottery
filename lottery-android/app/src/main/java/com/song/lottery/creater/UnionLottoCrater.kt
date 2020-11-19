package com.song.lottery.creater

import android.content.Context
import com.song.lottery.tactics.UnionLottoTactics
import com.song.lottery.utils.CvsReader
import kotlin.random.Random
import kotlin.random.nextInt

object UnionLottoCrater {

    fun generate(context: Context): Array<String> {
        val list = mutableListOf<IntArray>()
        repeat(1000) {
            val union = generateUnion(context)
            list.add(union)
        }
        val result = mutableListOf<String>()
        repeat(3) {
            result.add(list[Random.nextInt(IntRange(0, list.size))].contentToString())
        }
        return result.toTypedArray()
    }

    private fun generateUnion(context: Context): IntArray {
        val first = CvsReader.readCvs(context).first()
        val lastBlue = first.substring(first.lastIndex - 1).toInt()
        while (true) {
            val union = randomUnion()
            if (UnionLottoTactics.continuity(union, 2)
                && UnionLottoTactics.parity(union, 2)
                && UnionLottoTactics.parity(union.sliceArray(IntRange(0, 5)), 2)
                && UnionLottoTactics.compareBlue(union)
                && UnionLottoTactics.compareBlue(union, lastBlue)
                && UnionLottoTactics.repeatability(context, union)
            )
                return union
        }
    }

    private fun randomUnion(): IntArray =
        mutableListOf<Int>()
            .run {
                while (this.size < 6) {
                    val value = Random.nextInt(IntRange(1, 33))
                    if (value !in this) {
                        this.add(value)
                    }
                }
                this.sort()
                this.add(Random.nextInt(IntRange(1, 16)))
                return this.toIntArray()
            }

}