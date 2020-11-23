package com.song.lottery.ui.union

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.song.lottery.base.Model

class UnionLottoViewModel : ViewModel() {

    private val _list = MutableLiveData<List<Model>>()

    var list: LiveData<List<Model>> = _list

    fun setValue(data: List<Model>) {
        _list.apply { value = data }
    }
}