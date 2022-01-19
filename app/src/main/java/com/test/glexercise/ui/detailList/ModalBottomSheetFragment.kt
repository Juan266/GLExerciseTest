package com.test.glexercise.ui.detailList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.test.glexercise.databinding.ModalBottomSheetBinding

class ModalBottomSheetFragment:  BottomSheetDialogFragment() {

    companion object {
        const val TAG = "CustomBottomSheetDialogFragment"
        fun newInstance() = ModalBottomSheetFragment()
    }

    lateinit var binding: ModalBottomSheetBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = ModalBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDialog()
    }

    private fun initDialog() {
        requireDialog().window?.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        //requireDialog().window?.statusBarColor = requireContext().getColor(android.R.color.transparent)
        binding.fifthButton.setOnClickListener {
            dismiss()
            Toast.makeText(requireContext(), "Test Fifth buttom modal bottom sheet", Toast.LENGTH_SHORT).show()

        }
    }
}