package com.ahnsh1996.keepit.viewmodel

import android.view.ActionMode
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahnsh1996.keepit.model.KeepData
import com.ahnsh1996.keepit.repository.KeepDataRepository
import kotlinx.coroutines.launch
import java.util.*

class HomeViewModel(private val keepDataRepository: KeepDataRepository) : ViewModel() {

    private val _keepDataList = MutableLiveData<List<KeepData>>()
    val keepDataList: LiveData<List<KeepData>> get() = _keepDataList

    val selectedList = mutableListOf<UUID>()
    var editActionMode: ActionMode? = null

    init {
        loadKeepData()
    }

    fun loadKeepData() {
        viewModelScope.launch {
            _keepDataList.value = keepDataRepository.getKeepData()
        }
    }

    fun deleteKeepData(idList: List<UUID>, callback: () -> Unit) {
        viewModelScope.launch {
            keepDataRepository.deleteKeepData(idList)
            loadKeepData()
            callback()
        }
    }

    fun searchKeepData(keyword: String) {
        viewModelScope.launch {
            _keepDataList.value = keepDataRepository.searchKeepData(keyword)
        }
    }
}