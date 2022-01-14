package com.test.glexercise.ui.mainList

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.test.glexercise.R
import com.test.glexercise.ui.base.IActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), IActivity { //BaseActivity() {

    private val viewModelList by viewModels<MainListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        if (savedInstanceState == null) {
            initView(savedInstanceState)
        }
        addBackToActionBar()
    }

    private fun openFragment(fragment: Fragment, addToBackStack: Boolean) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(getContainerResId(), fragment)
        if (addToBackStack) {
            transaction.addToBackStack(null)
        }
        transaction.commitAllowingStateLoss()
    }

    private fun initView(savedInstanceState: Bundle?) {
        val fragment = getFragment()
        if (fragment != null) {
            openFragment(fragment, false)
        }
    }

    private fun getLayout() = R.layout.activity_full_screen

    private fun getContainerResId() = R.id.container

    fun addBackToActionBar() {
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
    }

    fun getFragment(): Fragment? {
        return MainListFragment()
    }


    override fun getMainListViewModel(): MainListViewModel {
        return viewModelList
    }

    override fun showError(errorMessage: String) {

    }

    override fun showError(errorMessageResId: Int) {

    }

    override fun showSuccess(messageResId: Int) {

    }

    override fun hideKeyboard() {

    }
}