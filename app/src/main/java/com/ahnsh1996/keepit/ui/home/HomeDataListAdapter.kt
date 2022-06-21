package com.ahnsh1996.keepit.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ahnsh1996.keepit.ui.common.KeepDataDiffCallback
import com.ahnsh1996.keepit.databinding.ItemHomeDataBinding
import com.ahnsh1996.keepit.model.KeepData
import com.ahnsh1996.keepit.viewmodel.HomeDataViewModel
import java.util.*

class HomeDataListAdapter(
    private val selectedList: List<UUID>,
    private val clickListener: HomeDataViewModel.OnItemClickEventListener
) : ListAdapter<KeepData, HomeDataListAdapter.HomeDataListViewHolder>(KeepDataDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeDataListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHomeDataBinding.inflate(inflater, parent, false)
        return HomeDataListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeDataListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class HomeDataListViewHolder(private val binding: ItemHomeDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(keepData: KeepData) {
            binding.viewModel = HomeDataViewModel(keepData, clickListener)
            binding.viewModel?.isSelected = selectedList.contains(keepData.id)
            binding.executePendingBindings()
        }
    }
}