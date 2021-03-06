package com.ahnsh1996.keepit.ui.common

import androidx.recyclerview.widget.DiffUtil

class StringDiffCallback : DiffUtil.ItemCallback<String>() {

    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem.length == newItem.length
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}