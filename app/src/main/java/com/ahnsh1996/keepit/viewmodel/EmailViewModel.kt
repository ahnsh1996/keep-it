package com.ahnsh1996.keepit.viewmodel

class EmailViewModel(val email: String, val clickListener: OnItemClickEventListener) {

    interface OnItemClickEventListener {
        fun onSendClick(email: String)
        fun onCopyClick(email: String)
    }
}