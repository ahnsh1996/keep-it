package com.ahnsh1996.keepit.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ahnsh1996.keepit.ui.common.StringDiffCallback
import com.ahnsh1996.keepit.databinding.ItemPhoneBinding
import com.ahnsh1996.keepit.viewmodel.PhoneViewModel

class PhoneListAdapter(private val clickListener: PhoneViewModel.OnItemClickEventListener) :
    ListAdapter<String, PhoneListAdapter.PhoneListViewHolder>(StringDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPhoneBinding.inflate(inflater, parent, false)
        return PhoneListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhoneListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PhoneListViewHolder(private val binding: ItemPhoneBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(phoneNumber: String) {
            binding.viewModel = PhoneViewModel(phoneNumber, clickListener)
        }
    }
}