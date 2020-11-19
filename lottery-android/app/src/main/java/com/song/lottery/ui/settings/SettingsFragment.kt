package com.song.lottery.ui.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.song.lottery.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}