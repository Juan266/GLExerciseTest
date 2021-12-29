package com.test.glexercise.ui.detailList

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.test.glexercise.EXTRA_ITEM_LIST
import com.test.glexercise.domain.model.ItemList
import com.test.glexercise.ui.base.BaseActivity

class DetailListActivity : BaseActivity() {

    companion object {
        fun getIntent(context: Context, itemList: ItemList): Intent {
            val intent = Intent(context, DetailListActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable(EXTRA_ITEM_LIST, itemList)
            intent.putExtras(bundle)
            return intent
        }
    }

    private fun getItemParcelable(): ItemList {
        return intent.getParcelableExtra(EXTRA_ITEM_LIST)!!
    }

    override fun getFragment(): Fragment {
        return DetailListFragment.newInstance(getItemParcelable())
    }
}