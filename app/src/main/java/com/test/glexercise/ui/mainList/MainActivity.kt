package com.test.glexercise.ui.mainList

import androidx.fragment.app.Fragment
import com.test.glexercise.ui.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun getFragment(): Fragment? {
        return MainListFragment()
    }

    override fun addBackToActionBar() {
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        }
    }
}