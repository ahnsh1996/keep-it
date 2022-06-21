package com.ahnsh1996.keepit.viewmodel

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import javax.net.ssl.SSLPeerUnverifiedException

class WebUrlViewModel(
    val webUrl: String,
    val clickListener: OnItemClickEventListener
) : BaseObservable() {

    @Bindable
    var title: String? = null
    @Bindable
    var imageUrl: String? = null

    init {
        var requestUrl = if (webUrl.startsWith("http")) webUrl else "https://$webUrl"
        CoroutineScope(Dispatchers.IO).launch {
            val doc = try {
                Jsoup.connect(requestUrl).get()
            } catch (e: SSLPeerUnverifiedException) {
                requestUrl = "http://$webUrl"
                Jsoup.connect(requestUrl).get()
            } catch (e: Exception) {
                null
            }

            val ogTags = doc?.select("meta[property^=og:]")
            ogTags?.forEach { tag ->
                when (tag.attr("property")) {
                    "og:image" -> imageUrl = tag.attr("content")
                    "og:title" -> title = tag.attr("content")
                }
            }

            if (title.isNullOrBlank()) {
                title = webUrl
            }
            notifyChange()
        }
    }

    interface OnItemClickEventListener {
        fun onClick(webUrl: String)
    }
}