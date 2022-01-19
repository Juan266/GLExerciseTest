package com.test.glexercise.ui.detailList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.test.glexercise.*
import com.test.glexercise.domain.model.ItemList
import com.test.glexercise.ui.base.BaseFragment
import kotlinx.android.synthetic.main.persistent_bottom_sheet.*

class DetailListFragment: BaseFragment() {

    private lateinit var binding: com.test.glexercise.databinding.FragmentDetailListConstBinding

    companion object {
        fun newInstance(itemParcelable: ItemList): DetailListFragment {
            val fragment = DetailListFragment()
            val args = Bundle()
            args.putParcelable(EXTRA_ITEM_LIST, itemParcelable)
            fragment.arguments = args
            return fragment
        }
    }

    private fun getDetailTitle(): String? {
        return  requireArguments().getParcelable<ItemList>(EXTRA_ITEM_LIST)?.title
    }

    private fun getDetailDescription(): String? {
        return  requireArguments().getParcelable<ItemList>(EXTRA_ITEM_LIST)?.description
    }

    private fun getDetailImageUrl(): String? {
        return  requireArguments().getParcelable<ItemList>(EXTRA_ITEM_LIST)?.image
    }

    override fun getLayout(): Int {
        return R.layout.fragment_detail_list_const
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        binding = DataBindingUtil.inflate(inflater, getLayout(), container, false)

        binding.detailListTitleConst.text = getDetailTitle()
        binding.detailListDescriptionConst.text = getDetailDescription()
        Glide.with(this).load(getDetailImageUrl()).into(binding.detailListImageConst)


        binding.detailListImageConst.setOnClickListener {
            /*if (callback.getBottomSheetBehavior().state == BottomSheetBehavior.STATE_EXPANDED)
                callback.getBottomSheetBehavior().state = BottomSheetBehavior.STATE_COLLAPSED
            else
                callback.getBottomSheetBehavior().state = BottomSheetBehavior.STATE_EXPANDED*/

            ModalBottomSheetFragment.newInstance().show(childFragmentManager,
                ModalBottomSheetFragment::class.java.canonicalName)
        }

        //Listening to State Changes of BottomSheet
        callback.getBottomSheetBehavior().addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                /*when (newState) {
                    BottomSheetBehavior.STATE_COLLAPSED -> Toast.makeText(getActivity(), "STATE_COLLAPSED", Toast.LENGTH_SHORT).show()
                    BottomSheetBehavior.STATE_EXPANDED -> Toast.makeText(getActivity(), "STATE_EXPANDED", Toast.LENGTH_SHORT).show()
                    BottomSheetBehavior.STATE_DRAGGING -> Toast.makeText(getActivity(), "STATE_DRAGGING", Toast.LENGTH_SHORT).show()
                    BottomSheetBehavior.STATE_SETTLING -> Toast.makeText(getActivity(), "STATE_SETTLING", Toast.LENGTH_SHORT).show()
                    BottomSheetBehavior.STATE_HIDDEN -> Toast.makeText(getActivity(), "STATE_HIDDEN", Toast.LENGTH_SHORT).show()
                    else -> Toast.makeText(getActivity(), "OTHER_STATE", Toast.LENGTH_SHORT).show()
                }*/
            }
        })

        requireActivity().bottom_sheet_item_one.setOnClickListener {
            callback.getBottomSheetBehavior().state = BottomSheetBehavior.STATE_COLLAPSED
            Toast.makeText(requireContext(), "TEST CLICK ITEM 1 BOTTOM SHEET", Toast.LENGTH_SHORT).show()
        }



        return binding.root
    }
}