package com.ahnsh1996.keepit.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ahnsh1996.keepit.ui.common.StringDiffCallback
import com.ahnsh1996.keepit.databinding.ItemWebUrlBinding
import com.ahnsh1996.keepit.viewmodel.WebUrlViewModel

class WebUrlListAdapter(private val clickListener: WebUrlViewModel.OnItemClickEventListener) :
    ListAdapter<String, WebUrlListAdapter.WebUrlListViewHolder>(StringDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WebUrlListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemWebUrlBinding.inflate(inflater, parent, false)
        return WebUrlListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WebUrlListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class WebUrlListViewHolder(private val binding: ItemWebUrlBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(webUrl: String) {
            binding.viewModel = WebUrlViewModel(webUrl, clickListener)
        }
    }
}