package com.song.lottery.utils

import java.io.File
import java.io.InputStream

object FileUtils {

    fun copy(src: InputStream, dest: File) {
//        val outputStream = FileOutputStream(dest)
//        val bufferedInputStream = BufferedInputStream(src)
//        val bufferedOutputStream = BufferedOutputStream(outputStream)
//        val buf = ByteArray(Constants.BUF_4096)
//        var len = bufferedInputStream.read(buf)
//        while (len != -1) {
//            bufferedOutputStream.write(buf, 0, len)
//            bufferedOutputStream.flush()
//            len = bufferedInputStream.read(buf)
//        }
//        bufferedInputStream.close()
//        bufferedOutputStream.close()

        val bufferedWriter = dest.bufferedWriter()
        src.bufferedReader().use {
            val buf = CharArray(DEFAULT_BUFFER_SIZE)
            var len = it.read(buf)
            while (len != -1) {
                bufferedWriter.write(buf, 0, len)
                bufferedWriter.flush()
                len = it.read(buf)
            }
        }

    }

}