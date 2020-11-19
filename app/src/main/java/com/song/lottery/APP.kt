package com.song.lottery

import android.content.Context
import android.os.SystemClock
import androidx.multidex.MultiDexApplication
import com.song.lottery.utils.CvsReader
import kotlin.concurrent.thread

class APP : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        init(this)
    }

    private fun init(context: Context) {
        thread {
            kotlin.run {
                val currentThreadTimeMillis = SystemClock.currentThreadTimeMillis()
                CvsReader.initCvs(context)
                println("Cvs load time: ${SystemClock.currentThreadTimeMillis() - currentThreadTimeMillis}")
            }
        }
    }

}