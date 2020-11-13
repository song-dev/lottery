package com.song.lottery

import android.content.Context
import android.os.Bundle
import android.os.Looper
import android.os.SystemClock
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.song.lottery.utils.CvsReader
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_union_lotto, R.id.navigation_super_lotto, R.id.navigation_settings
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        init(applicationContext)
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