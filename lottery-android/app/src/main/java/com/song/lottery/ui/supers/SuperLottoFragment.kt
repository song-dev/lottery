package com.song.lottery.ui.supers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.song.lottery.R

class SuperLottoFragment : Fragment() {

    private lateinit var superLottoViewModel: SuperLottoViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        superLottoViewModel =
                ViewModelProvider(this).get(SuperLottoViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_super_lotto, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        superLottoViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}