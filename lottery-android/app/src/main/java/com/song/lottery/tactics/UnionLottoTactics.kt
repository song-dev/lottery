package com.song.lottery.tactics

import android.content.Context
import android.widget.Toast
import com.song.lottery.utils.CvsReader
import kotlin.math.abs

object UnionLottoTactics {

    /**
     * # 双色球重复概率为 0，红球重复出现次数仅为 2 次
    # 蓝球和红球不相同概率 82%
    # 红球奇偶相差个数最大为 2 为 83%
    # 所有球奇偶相差个数最大为 2 为 91%
    # 最多2个球连续概率 90%
    # 连续两期蓝球不相同概率 89%
     */

    /**
     * 不得重复出现已经出现号码
     */
    fun repeatability(context: Context, item: IntArray): Boolean {
        val history = CvsReader.readCvs(context)
        val union = item.joinToString(separator = "") { "%02d".format(it) }
        return if (union in history) false.also {
            println("=======================history number=============================")
//            Toast.makeText(context, "history number", Toast.LENGTH_SHORT).show()
        } else true
    }

    /**
     * 连续两期蓝球不相同
     */
    fun compareBlue(item: IntArray, blue: Int): Boolean {
        return blue != item.last()
    }

    /**
     * 蓝球不和红球相同
     */
    fun compareBlue(item: IntArray): Boolean {
        if (item.size != 7) {
            throw IllegalArgumentException("compareBlue: union lotto size is not 7.")
        }
        val blue = item.last()
        val red = item.slice(IntRange(0, item.lastIndex - 1))
        return blue !in red
    }

    /**
     * 奇偶校验
     */
    fun parity(item: IntArray, max: Int): Boolean {
        var count = 0
        for (ball in item) {
            if (ball % 2 == 0) {
                count++
            }
        }
        val size = item.size
        return abs(2 * count - size) < size - max
    }

    /**
     * 连续性校验
     */
    fun continuity(item: IntArray, max: Int): Boolean {
        var count = 0
        loop@ for (len in item.size downTo max) {
            for (start in 0..(item.size - len)) {
                if (isContinuity(item.sliceArray(IntRange(start, start + len - 1)))) {
                    count = len
                    break@loop
                }
            }
        }
        if (count > max) {
            return false
        }
        return true
    }

    /**
     * 判断是否是连续数组
     */
    private fun isContinuity(item: IntArray): Boolean {
        var i = 0
        while (i < item.lastIndex) {
            if (item[i] + 1 == item[i + 1]) {
                i++
            } else {
                return false
            }
        }
        return true
    }

}