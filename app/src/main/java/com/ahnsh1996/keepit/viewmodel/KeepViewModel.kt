package com.ahnsh1996.keepit.viewmodel

import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahnsh1996.keepit.model.KeepData
import com.ahnsh1996.keepit.repository.KeepDataRepository
import kotlinx.coroutines.launch

class KeepViewModel(private val keepDataRepository: KeepDataRepository) : ViewModel() {

    fun addKeepData(title: String?, content: String, textView: TextView, finish: () -> Unit) {
        viewModelScope.launch {
            var titleToAdd = title
            if (titleToAdd.isNullOrBlank()) {
                titleToAdd = "Note ${keepDataRepository.getKeepDataCount() + 1}"
            }
            val keepData = KeepData(title = titleToAdd, data = content)
            textView.text = content

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