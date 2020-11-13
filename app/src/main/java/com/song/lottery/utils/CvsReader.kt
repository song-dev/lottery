package com.song.lottery.utils

import android.content.Context
import com.opencsv.CSVParserBuilder
import com.opencsv.CSVReaderBuilder
import java.io.*

object CvsReader {

    fun readByCVSReader(context: Context) {
        copy(context.assets.open("union_lotto.csv"), File(context.filesDir, "union_lotto.csv"))
        val csvReader = CSVReaderBuilder(FileReader(File(context.filesDir, "union_lotto.csv")))
            .withCSVParser(CSVParserBuilder().withSeparator(',').build())
            .build()
        val header = csvReader.readNext()
        var line: Array<String>? = csvReader.readNext()
        while (line != null) {
            println(line[0])
            line = csvReader.readNext()
        }
    }

    private fun copy(src: InputStream, dest: File) {
        val outputStream = FileOutputStream(dest)
        val bufferedInputStream = BufferedInputStream(src)
        val bufferedOutputStream = BufferedOutputStream(outputStream)
        val buf = ByteArray(4096)
        var len = bufferedInputStream.read(buf)
        while (len != -1) {
            bufferedOutputStream.write(buf, 0, len)
            bufferedOutputStream.flush()
            len = bufferedInputStream.read(buf)
        }
        bufferedInputStream.close()
        bufferedOutputStream.close()
    }

    private val list: MutableList<String> = mutableListOf()

    fun readCvs(context: Context): Array<String> {
        if (list.isEmpty()) {
            val bufferedReader =
                BufferedReader(InputStreamReader(context.assets.open("union_lotto.csv"), "utf-8"))
            var line: String? = bufferedReader.readLine()
            while (line != null) {
                val split = line.split(",")
                if (split.isNotEmpty()) {
                    list.add(split.subList(2, 9).joinToString(separator = ""))
                }
                line = bufferedReader.readLine()
            }
            if (list.isNotEmpty()) {
                list.removeFirst()
            }
        }
        return list.toTypedArray()
    }

    fun initCvs(context: Context) {
        readCvs(context)
    }

}