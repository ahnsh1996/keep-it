package com.ahnsh1996.keepit.viewmodel

import com.ahnsh1996.keepit.model.KeepData
import java.text.SimpleDateFormat
import java.util.*

class HomeDataViewModel(
    val keepData: KeepData,
    val clickListener: OnItemClickEventListener
) {

    val data get() = keepData.data
    val date get() = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(keepData.lastWriteDate)
    var isSelected = false

    interface OnItemClickEventListener {
        fun onClick(keepData: KeepData, homeDataViewModel: HomeDataViewModel)
        fun onLongClick(dataId: UUID): Boolean
        fun onCopyClick(data: String)
    }
}