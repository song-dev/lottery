package com.song.lottery.ui.settings

import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import androidx.preference.PreferenceFragmentCompat
import com.song.lottery.R

class SettingsFragment : PreferenceFragmentCompat(),
    SharedPreferences.OnSharedPreferenceChangeListener {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }

    override fun onResume() {
        super.onResume()
        preferenceManager.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceManager.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (TextUtils.equals(key, "settings_language")) {
            // 将最近几期篮球添加到杀号里面
        }
    }
}