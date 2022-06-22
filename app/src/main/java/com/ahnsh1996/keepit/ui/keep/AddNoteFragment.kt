package com.ahnsh1996.keepit.ui.keep

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ahnsh1996.keepit.databinding.FragmentAddNoteBinding
import com.ahnsh1996.keepit.ui.common.ViewModelFactory
import com.ahnsh1996.keepit.viewmodel.KeepViewModel

class AddNoteFragment : Fragment() {

    private val viewModel: KeepViewModel by viewModels() { ViewModelFactory(requireContext()) }
    private lateinit var binding: FragmentAddNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentAddNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonAddNote.setOnClickListener {
            val title = binding.edittextTitle.text.toString()
            val content = binding.edittextContent.text.toString()
            viewModel.addKeepData(title, content, binding.textviewCollection) {
                findNavController().popBackStack()
            }
        }
    }
}