package com.song.lottery.ui.union

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import butterknife.BindView
import butterknife.ButterKnife
import com.song.lottery.R
import com.song.lottery.base.GenerateUnionModel
import com.song.lottery.base.MultiTypeAdapter
import com.song.lottery.base.Normal
import com.song.lottery.base.Visitable
import com.song.lottery.creater.UnionLottoCrater
import com.song.lottery.utils.CvsReader

class UnionLottoFragment : Fragment() {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.swipe_refresh_layout)
    lateinit var swipeRefreshLayout: SwipeRefreshLayout

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.recycler_view)
    lateinit var recyclerView: RecyclerView

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
        ButterKnife.bind(this, root)
        val list = listOf<Visitable>()
        val adapter = MultiTypeAdapter(list)
        recyclerView = root.findViewById(R.id.recycler_view)
        swipeRefreshLayout = root.findViewById(R.id.swipe_refresh_layout)
        recyclerView.layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
        recyclerView.adapter = adapter
        unionLottoViewModel.list.observe(viewLifecycleOwner, Observer {
            swipeRefreshLayout.isRefreshing = false
            adapter.updateData(it)
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSwipeRefreshLayout()
    }

    private fun setSwipeRefreshLayout() {
        swipeRefreshLayout.setColorSchemeResources(
            android.R.color.holo_blue_light,
            android.R.color.holo_red_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_green_light
        )
        swipeRefreshLayout.setOnRefreshListener { refreshData() }
        swipeRefreshLayout.post {
            swipeRefreshLayout.isRefreshing = true
            refreshData()
        }
    }

    private fun refreshData() {
        // 获取数据
        val list = CvsReader.readCvs(requireContext()).sliceArray(IntRange(0, 4))
            .joinToString(separator = "\n")
        val union = UnionLottoCrater.generate(requireContext()).joinToString("\n")
        unionLottoViewModel.setValue(listOf<Visitable>(Normal(list),GenerateUnionModel(union)))
    }
}