package com.song.lottery.ui.union

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.song.lottery.base.Visitable

class UnionLottoViewModel : ViewModel() {

//    private val _text = MutableLiveData<String>().apply {
//        value = "This is home Fragment"
//    }
//    val text: LiveData<String> = _text
//
//    fun setValue(string: String) {
//        _text.apply { value = string }
//    }

    private val _list = MutableLiveData<List<Visitable>>()

    var list: LiveData<List<Visitable>> = _list

    fun setValue(data: List<Visitable>) {
        _list.apply { value = data }
    }
}