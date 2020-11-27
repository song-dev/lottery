package com.song.lottery.ui.union

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import butterknife.BindView
import butterknife.ButterKnife
import com.song.lottery.R
import com.song.lottery.adapter.*
import com.song.lottery.creater.UnionLottoCrater
import com.song.lottery.ui.union.model.GenerateUnionModel
import com.song.lottery.ui.union.model.HistoryContentModel
import com.song.lottery.ui.union.model.HistoryTitleModel
import com.song.lottery.utils.CvsReader
import kotlin.concurrent.thread

class UnionLottoFragment : Fragment() {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.swipe_refresh_layout)
    lateinit var swipeRefreshLayout: SwipeRefreshLayout

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.recycler_view)
    lateinit var recyclerView: RecyclerView

    private lateinit var unionLottoViewModel: UnionLottoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        unionLottoViewModel =
            ViewModelProvider(this).get(UnionLottoViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_union_lotto, container, false)
        ButterKnife.bind(this, root)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = listOf<Model>()
        val adapter = LotteryAdapter(list)
        recyclerView.layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
        recyclerView.adapter = adapter
        unionLottoViewModel.list.observe(viewLifecycleOwner, Observer {
            swipeRefreshLayout.isRefreshing = false
            adapter.updateData(it)
        })
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
        thread {
            kotlin.run {
                // 获取数据
                val union = UnionLottoCrater.generate(requireContext()).joinToString("\n")
                val list = CvsReader.readByCVSReader(requireContext())
                val item = list.firstOrNull()
                item?.let {
                    activity?.let {
                        it.runOnUiThread {
                            val data = mutableListOf<Model>(
                                HistoryTitleModel(R.mipmap.ic_launcher_round, "双色球", item)
                            )
                            list.slice(IntRange(0, 4)).forEach { content ->
                                data.add(HistoryContentModel(content))
                            }
                            data.add(GenerateUnionModel(union))
                            unionLottoViewModel.setValue(data)
                        }
                    }
                }
            }
        }
    }
}