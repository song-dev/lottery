package com.song.lottery.creater

import android.content.Context
import com.song.lottery.tactics.UnionLottoTactics
import com.song.lottery.utils.CvsReader
import kotlin.random.Random
import kotlin.random.nextInt

object UnionLottoCrater {

    fun generate(context: Context): Array<String> {
        var count = 0
        val list = mutableListOf<IntArray>()
        while (count < 1000) {
            val union = generateUnion(context)
            list.add(union)
            count++
        }
        val result = mutableListOf<String>()
        result.add(list[Random.nextInt(IntRange(0, list.size))].contentToString())
        result.add(list[Random.nextInt(IntRange(0, list.size))].contentToString())
        result.add(list[Random.nextInt(IntRange(0, list.size))].contentToString())
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

    private fun randomUnion(): IntArray {
        val union = mutableListOf<Int>()
        while (union.size < 6) {
            val value = Random.nextInt(IntRange(1, 33))
            if (value !in union) {
                union.add(value)
            }
        }
        union.sort()
        union.add(Random.nextInt(IntRange(1, 16)))
        return union.toIntArray()
    }

}