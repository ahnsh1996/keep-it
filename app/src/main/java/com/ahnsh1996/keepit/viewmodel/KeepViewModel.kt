package com.ahnsh1996.keepit.viewmodel

import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahnsh1996.keepit.repository.KeepDataRepository
import com.ahnsh1996.keepit.model.KeepData
import kotlinx.coroutines.launch

class KeepViewModel(private val keepDataRepository: KeepDataRepository) : ViewModel() {

    fun addKeepData(selectText: String, textView: TextView, finish: () -> Unit) {
        viewModelScope.launch {
            val title = "Note ${keepDataRepository.getKeepDataCount() + 1}"
            val keepData = KeepData(title = title, data = selectText)
            textView.text = selectText

            textView.urls.forEach { urlSpan ->
                if (urlSpan.url.startsWith("tel:")) {
                    keepData.phoneList.add(urlSpan.url.substring("tel:".length))
                } else if (urlSpan.url.startsWith("mailto:")) {
                    keepData.emailList.add(urlSpan.url.substring("mailto:".length))
                } else {
                    keepData.webUrlList.add(urlSpan.url)
                }
            }
            keepDataRepository.addKeepData(keepData)
            finish()
        }
    }
}