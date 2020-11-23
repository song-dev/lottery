package com.song.lottery.utils

import android.content.Context
import com.opencsv.CSVParserBuilder
import com.opencsv.CSVReaderBuilder
import java.io.*

object CvsReader {

    private val origin: MutableList<List<String>> = mutableListOf()

    private fun read(context: Context): MutableList<List<String>> {
        if (origin.isEmpty()) {
            context.assets.open(Constants.UNION_LOTTO_FILE).bufferedReader().useLines {
                it.forEach { line ->
                    if (line.isNotBlank()) {
                        val split = line.split(",")
                        if (split.isNotEmpty()) {
                            val item = mutableListOf<String>()
                            item.addAll(split.subList(1, 9))
                            item.add(split[10])
                            item.add(split[16])
                            origin.add(item)
                        }
                    }
                }
            }
            if (origin.isNotEmpty()) {
                origin.removeFirst()
            }
        }
        return origin
    }

    fun readByCVSReader(context: Context): MutableList<List<String>> {
        if (origin.isEmpty()) {
            val csvReader =
                CSVReaderBuilder(context.assets.open(Constants.UNION_LOTTO_FILE).bufferedReader())
                    .withCSVParser(CSVParserBuilder().withSeparator(',').build())
                    .build()
            val header = csvReader.readNext()
            var line: Array<String>? = csvReader.readNext()
            while (line != null) {
                val item = mutableListOf<String>()
                item.addAll(line.sliceArray(IntRange(1, 8)))
                item.add(line[10])
                item.add(line[16])
                origin.add(item)
                line = csvReader.readNext()
            }
        }
        return origin
    }

    private val list: MutableList<String> = mutableListOf()

    fun readCvs(context: Context): Array<String> {
        if (list.isEmpty()) {
            context.assets.open(Constants.UNION_LOTTO_FILE).bufferedReader().use {
                var line: String? = it.readLine()
                while (line != null) {
                    val split = line.split(",")
                    if (split.isNotEmpty()) {
                        list.add(split.subList(2, 9).joinToString(separator = ""))
                    }
                    line = it.readLine()
                }
            }
            if (list.isNotEmpty()) {
                list.removeFirst()
            }
        }
        return list.toTypedArray()
    }

    fun initCvs(context: Context) {
        readByCVSReader(context)
    }

}