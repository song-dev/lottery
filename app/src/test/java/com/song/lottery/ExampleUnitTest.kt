package com.song.lottery

import com.song.lottery.tactics.UnionLottoTactics
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        println(UnionLottoTactics.continuity(intArrayOf(0, 2, 3, 4, 6, 7), 3))
        println(intArrayOf(0, 2, 3, 4, 6, 7).joinToString(separator = "") { "%02d".format(it) })
        assertEquals(4, 2 + 2)
    }
}