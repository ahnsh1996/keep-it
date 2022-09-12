package com.ahnsh1996.keepit.ui.home

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahnsh1996.keepit.R
import com.ahnsh1996.keepit.databinding.FragmentHomeBinding
import com.ahnsh1996.keepit.model.KeepData
import com.ahnsh1996.keepit.ui.common.ViewModelFactory
import com.ahnsh1996.keepit.viewmodel.HomeDataViewModel
import com.ahnsh1996.keepit.viewmodel.HomeViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels() { ViewModelFactory(requireContext()) }
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.keepData.collectLatest { pagedKeepData ->
                    (binding.recyclerviewKeepdataList.adapter as HomeDataPagingAdapter).submitData(
                        pagedKeepData
                    )
                }
            }
        }

        setMenuProvider()
        setPagingAdapter()

        binding.buttonAddNote.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_add_note)
        }

        if (viewModel.editActionMode != null) {
            activity?.startActionMode(ActionModeCallback())
        }
    }

    private fun setMenuProvider() {
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.home_context_menu, menu)

                val searchItem = menu.findItem(R.id.menu_search)
                val searchView = searchItem.actionView as SearchView
                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        newText?.let {
                            viewModel.searchKeepData(newText.trim())
                        }
                        return false
                    }
                })
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.menu_edit -> {
                        viewModel.editActionMode = activity?.startActionMode(ActionModeCallback())
                        true
                    }
                    else -> false
                }
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun setPagingAdapter() {
        val adapter = HomeDataPagingAdapter(
            viewModel.selectedList,
            object : HomeDataViewModel.OnItemClickEventListener {
                override fun onClick(keepData: KeepData, homeDataViewModel: HomeDataViewModel) {
                    if (viewModel.editActionMode == null) {
                        val action = HomeFragmentDirections.actionHomeToKeepDataDetail(keepData)
                        findNavController().navigate(action)
                    } else {
                        if (!viewModel.selectedList.contains(keepData.id)) {
                            viewModel.selectedList.add(keepData.id)
                        } else {
                            viewModel.selectedList.remove(keepData.id)
                        }
                        binding.recyclerviewKeepdataList.adapter?.notifyDataSetChanged()
                    }
                }

                override fun onLongClick(dataId: UUID): Boolean {
                    if (viewModel.editActionMode == null) {
                        viewModel.editActionMode = activity?.startActionMode(ActionModeCallback())
                        viewModel.selectedList.add(dataId)
                        binding.recyclerviewKeepdataList.adapter?.notifyDataSetChanged()
                    }
                    return false
                }

                override fun onCopyClick(data: String) {
                    val clipboard =
                        activity?.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                    val clip: ClipData = ClipData.newPlainText("data", data)
                    clipboard.setPrimaryClip(clip)
                    Toast.makeText(requireContext(), "복사되었습니다.", Toast.LENGTH_SHORT).show()
                }
            })

        binding.recyclerviewKeepdataList.adapter = adapter
        binding.recyclerviewKeepdataList.layoutManager = LinearLayoutManager(context)
    }

    inner class ActionModeCallback : ActionMode.Callback {
        override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
            val inflater: MenuInflater = mode.menuInflater
            inflater.inflate(R.menu.edit_mode_context_menu, menu)
            return true
        }

        override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
            return false
        }

        override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
            return when (item.itemId) {
                R.id.menu_delete -> {
                    Toast.makeText(
                        requireContext(),
                        viewModel.selectedList.size.toString() + "개 항목 삭제됨.",
                        Toast.LENGTH_SHORT
                    ).show()
                    viewModel.deleteKeepData(viewModel.selectedList) { mode.finish() }
                    true
                }
                else -> false
            }
        }

        override fun onDestroyActionMode(mode: ActionMode) {
            viewModel.selectedList.clear()
            binding.recyclerviewKeepdataList.adapter?.notifyDataSetChanged()
            viewModel.editActionMode = null
        }
    }
}