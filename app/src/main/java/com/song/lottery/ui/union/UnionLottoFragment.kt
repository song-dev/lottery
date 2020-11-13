package com.song.lottery.ui.union

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.song.lottery.R
import com.song.lottery.creater.UnionLottoCrater
import com.song.lottery.utils.CvsReader

class UnionLottoFragment : Fragment() {

    private lateinit var unionLottoViewModel: UnionLottoViewModel
    private lateinit var view: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        unionLottoViewModel =
            ViewModelProvider(this).get(UnionLottoViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_union_lotto, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        view = root.findViewById<TextView>(R.id.textView)
        unionLottoViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        textView.setOnClickListener {
            unionLottoViewModel.setValue(
                UnionLottoCrater.generate(root.context).joinToString("\n")
            )
            view.text = CvsReader.readCvs(root.context).sliceArray(IntRange(0, 4))
                .joinToString(separator = "\n")
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}