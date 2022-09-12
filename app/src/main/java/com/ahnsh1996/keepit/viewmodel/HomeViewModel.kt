package com.ahnsh1996.keepit.viewmodel

import android.view.ActionMode
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ahnsh1996.keepit.repository.KeepDataRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.*

class HomeViewModel(private val keepDataRepository: KeepDataRepository) : ViewModel() {

    private val searchKeyword: MutableStateFlow<String> = MutableStateFlow("")

    val keepData = searchKeyword.flatMapLatest { keyword ->
        if (keyword.isBlank()) {
            keepDataRepository.getAllKeepData()
        } else {
            keepDataRepository.searchKeepData(keyword)
        }
    }.cachedIn(viewModelScope)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), PagingData.empty())

    val selectedList = mutableListOf<UUID>()
    var editActionMode: ActionMode? = null

    fun deleteKeepData(idList: List<UUID>, callback: () -> Unit) {
        viewModelScope.launch {
            keepDataRepository.deleteKeepData(idList)
            callback()
        }
    }

    fun searchKeepData(keyword: String) {
        searchKeyword.value = keyword
    }
}