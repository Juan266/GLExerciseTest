package com.test.glexercise.ui.detailList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import com.squareup.picasso.Picasso
import com.test.glexercise.*
import com.test.glexercise.domain.model.ItemList
import com.test.glexercise.ui.base.BaseFragment

class DetailListFragment: BaseFragment() {

    private lateinit var binding: com.test.glexercise.databinding.FragmentDetailListBinding

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
        return R.layout.fragment_detail_list
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        binding = DataBindingUtil.inflate(inflater, getLayout(), container, false)

        binding.detailListTitle.text = getDetailTitle()
        binding.detailListDescription.text = getDetailDescription()
        Picasso.get().load(getDetailImageUrl()).into(binding.detailListImage)

        return binding.root
    }
}