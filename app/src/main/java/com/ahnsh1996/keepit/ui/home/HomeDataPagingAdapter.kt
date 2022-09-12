package com.ahnsh1996.keepit.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ahnsh1996.keepit.databinding.ItemHomeDataBinding
import com.ahnsh1996.keepit.model.KeepData
import com.ahnsh1996.keepit.ui.common.KeepDataDiffCallback
import com.ahnsh1996.keepit.viewmodel.HomeDataViewModel
import java.util.*

class HomeDataPagingAdapter(
    private val selectedList: List<UUID>,
    private val clickListener: HomeDataViewModel.OnItemClickEventListener
) : PagingDataAdapter<KeepData, HomeDataPagingAdapter.HomeDataViewHolder>(KeepDataDiffCallback()) {

    override fun onBindViewHolder(holder: HomeDataViewHolder, position: Int) {
        getItem(position)?.let { pagedData ->
            holder.bind(pagedData)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeDataViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHomeDataBinding.inflate(inflater, parent, false)
        return HomeDataViewHolder(binding)
    }

    inner class HomeDataViewHolder(private val binding: ItemHomeDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(keepData: KeepData) {
            binding.viewModel = HomeDataViewModel(keepData, clickListener)
            binding.viewModel?.isSelected = selectedList.contains(keepData.id)
            binding.executePendingBindings()
        }
    }
}