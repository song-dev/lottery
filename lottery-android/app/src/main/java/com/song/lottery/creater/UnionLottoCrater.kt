package com.song.lottery.creater

import android.content.Context
import androidx.preference.PreferenceManager
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
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            val redSet = mutableSetOf<Int>().apply {
                preferences.getStringSet("red_ball_kill_number", null)?.forEach {
                    this.add(it.toInt())
                }
            }
            val blueSet = mutableSetOf<Int>().apply {
                preferences.getStringSet("blue_ball_kill_number", null)?.forEach {
                    this.add(it.toInt())
                }
            }
            val union = randomUnion(redSet, blueSet)
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

    fun randomUnion(redKill: Set<Int>?, blueKill: Set<Int>?): IntArray =
        mutableListOf<Int>()
            .run {
                val red = mutableListOf<Int>().apply {
                    for (i in 1..33) {
                        add(i)
                    }
                }
                redKill?.forEach {
                    if (it in red) {
                        red.remove(it)
                    }
                }
                val blue = mutableListOf<Int>()
                    .apply {
                        for (i in 1..16) {
                            add(i)
                        }
                    }
                blueKill?.forEach {
                    if (it in blue) {
                        blue.remove(it)
                    }
                }
                while (this.size < 6) {
                    this.add(red.removeAt(Random.nextInt(0, red.size)))
                }
                this.sort()
                this.add(blue.removeAt(Random.nextInt(0, blue.size)))
                return this.toIntArray()
            }

}