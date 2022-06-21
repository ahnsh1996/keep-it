package com.ahnsh1996.keepit.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ahnsh1996.keepit.databinding.ItemEmailBinding
import com.ahnsh1996.keepit.ui.common.StringDiffCallback
import com.ahnsh1996.keepit.viewmodel.EmailViewModel

class EmailListAdapter(private val clickListener: EmailViewModel.OnItemClickEventListener) :
    ListAdapter<String, EmailListAdapter.EmailListViewHolder>(StringDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmailListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemEmailBinding.inflate(inflater, parent, false)
        return EmailListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmailListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class EmailListViewHolder(private val binding: ItemEmailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(email: String) {
            binding.viewModel = EmailViewModel(email, clickListener)
        }
    }
}