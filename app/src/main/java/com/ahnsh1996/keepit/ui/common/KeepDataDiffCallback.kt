package com.ahnsh1996.keepit.ui.common

import androidx.recyclerview.widget.DiffUtil
import com.ahnsh1996.keepit.model.KeepData

class KeepDataDiffCallback : DiffUtil.ItemCallback<KeepData>() {

    override fun areItemsTheSame(oldItem: KeepData, newItem: KeepData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: KeepData, newItem: KeepData): Boolean {
        return oldItem == newItem
    }
}