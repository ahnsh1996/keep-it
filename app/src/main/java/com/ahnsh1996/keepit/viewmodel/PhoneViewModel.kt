package com.ahnsh1996.keepit.viewmodel

class PhoneViewModel(val phoneNumber: String, val clickListener: OnItemClickEventListener) {

    interface OnItemClickEventListener {
        fun onCallClick(phoneNumber: String)
        fun onCopyClick(phoneNumber: String)
    }
}