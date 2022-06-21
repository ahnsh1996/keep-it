package com.ahnsh1996.keepit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ahnsh1996.keepit.model.KeepData
import java.text.SimpleDateFormat

class KeepDataDetailViewModel(): ViewModel() {

    lateinit var keepData: KeepData
    var currentTab = MutableLiveData<Int>()
    val lastWriteDate get() = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(keepData.lastWriteDate)
    val isWebListEmpty get() = keepData.webUrlList.isEmpty()
    val isPhoneListEmpty get() = keepData.phoneList.isEmpty()
    val isEmailListEmpty get() = keepData.emailList.isEmpty()
    val data get() = keepData.data
    val title get() = keepData.title

    init {
        currentTab.postValue(0)
    }
}