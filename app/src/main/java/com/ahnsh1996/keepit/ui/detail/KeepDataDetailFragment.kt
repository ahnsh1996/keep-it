package com.ahnsh1996.keepit.ui.detail

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahnsh1996.keepit.databinding.FragmentKeepDataDetailBinding
import com.ahnsh1996.keepit.viewmodel.EmailViewModel
import com.ahnsh1996.keepit.viewmodel.KeepDataDetailViewModel
import com.ahnsh1996.keepit.viewmodel.PhoneViewModel
import com.ahnsh1996.keepit.viewmodel.WebUrlViewModel
import com.google.android.material.tabs.TabLayout

class KeepDataDetailFragment : Fragment() {

    private val viewModel: KeepDataDetailViewModel by viewModels()
    private lateinit var binding: FragmentKeepDataDetailBinding
    private val args: KeepDataDetailFragmentArgs by navArgs<KeepDataDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentKeepDataDetailBinding.inflate(inflater, container, false)
        viewModel.keepData = args.keepData
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setTabLayoutListener()
        setWebUrlListAdapter()
        setPhoneListAdapter()
        setEmailListAdapter()
    }

    private fun setTabLayoutListener() {
        binding.tablayoutCollection.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    viewModel.currentTab.postValue(tab.position)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun setWebUrlListAdapter() {
        val adapter = WebUrlListAdapter(object : WebUrlViewModel.OnItemClickEventListener {
            override fun onClick(webUrl: String) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(webUrl))
                startActivity(intent)
            }
        }).apply { submitList(viewModel.keepData.webUrlList) }

        binding.recyclerviewWebLinkList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerviewWebLinkList.adapter = adapter
    }

    private fun setPhoneListAdapter() {
        val adapter = PhoneListAdapter(object : PhoneViewModel.OnItemClickEventListener {
            override fun onCallClick(phoneNumber: String) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("tel:${phoneNumber}"))
                startActivity(intent)
            }

            override fun onCopyClick(phoneNumber: String) {
                val clipboard =
                    activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip: ClipData = ClipData.newPlainText("data", phoneNumber)
                clipboard.setPrimaryClip(clip)
                Toast.makeText(requireContext(), "복사되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }).apply { submitList(viewModel.keepData.phoneList) }
        binding.recyclerviewPhoneList.adapter = adapter
        binding.recyclerviewPhoneList.layoutManager =
            GridLayoutManager(context, 3, GridLayoutManager.HORIZONTAL, false)
    }

    private fun setEmailListAdapter() {
        val adapter = EmailListAdapter(object : EmailViewModel.OnItemClickEventListener {
            override fun onSendClick(email: String) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("mailto:${email}"))
                startActivity(intent)
            }

            override fun onCopyClick(email: String) {
                val clipboard =
                    activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip: ClipData = ClipData.newPlainText("data", email)
                clipboard.setPrimaryClip(clip)
                Toast.makeText(requireContext(), "복사되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }).apply { submitList(viewModel.keepData.emailList) }
        binding.recyclerviewEmailList.adapter = adapter
        binding.recyclerviewEmailList.layoutManager =
            GridLayoutManager(context, 3, GridLayoutManager.HORIZONTAL, false)
    }
}