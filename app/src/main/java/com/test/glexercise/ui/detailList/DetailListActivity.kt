package com.test.glexercise.ui.detailList

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.test.glexercise.EXTRA_ITEM_LIST
import com.test.glexercise.R
import com.test.glexercise.domain.model.ItemList
import com.test.glexercise.ui.base.BaseActivity
import kotlinx.android.synthetic.main.persistent_bottom_sheet.*

class DetailListActivity : BaseActivity() {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    companion object {
        fun getIntent(context: Context, itemList: ItemList): Intent {
            val intent = Intent(context, DetailListActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable(EXTRA_ITEM_LIST, itemList)
            intent.putExtras(bundle)
            return intent
        }
    }

    override fun getLayout() = R.layout.activity_detail_list

    override fun bottomSheetView() {
        bottomSheetBehavior = BottomSheetBehavior.from(persistent_bottom_sheet)
    }

    private fun getItemParcelable(): ItemList {
        return intent.getParcelableExtra(EXTRA_ITEM_LIST)!!
    }

    override fun getFragment(): Fragment {
        return DetailListFragment.newInstance(getItemParcelable())
    }

    override fun getBottomSheetBehavior(): BottomSheetBehavior<ConstraintLayout> {
        return bottomSheetBehavior
    }
}