package com.test.glexercise.ui.mainList

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.glexercise.R
import com.test.glexercise.databinding.FragmentMainListBinding
import com.test.glexercise.domain.model.ItemList
import com.test.glexercise.ui.base.BaseActivity
import com.test.glexercise.ui.base.IActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_full_screen.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), IActivity { //BaseActivity() {

    //private lateinit var listMain: List<ItemList>
    //private val adapterMainList: MainListAdapter = MainListAdapter(this)
    //private lateinit var binding: FragmentMainListBinding


    private val viewModelList by viewModels<MainListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            initView(savedInstanceState)
        }
        addBackToActionBar()
        /*binding = FragmentMainListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //binding.tvTest.text = "This is a hardcode text"

        setMainListView()*/

    }

    /*private fun setMainListView() {
        binding.mainList.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,
            false )
        val dividerItemDecoration = DividerItemDecoration(this,LinearLayoutManager.VERTICAL)
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider_list)!!)
        binding.mainList.addItemDecoration(dividerItemDecoration)
        //binding.mainListSwipeRefresh.setOnRefreshListener(this)

        viewModelList.getMainList()
        viewModelList.listData.observe(this) {
            if (it != null) {
                listMain = it.data!!.toList()
                setMainList(listMain)
            }
        }
        viewModelList.refreshing.observe(this) {
            //binding.mainListSwipeRefresh.isRefreshing = it!!
        }
        viewModelList.showProgress.observe(this) {
            binding.mainListProgressBar.visibility = if (it) (View.VISIBLE) else (View.GONE)
        }
    }*/

    /*private fun setMainList(list: List<ItemList>) {
        binding.mainList.adapter = adapterMainList
        adapterMainList.updateMainList(list)
        Toast.makeText(this, list.get(0).title, Toast.LENGTH_SHORT).show()
    }*/

    protected fun openFragment(fragment: Fragment, addToBackStack: Boolean) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(getContainerResId(), fragment)
        if (addToBackStack) {
            transaction.addToBackStack(null)
        }
        transaction.commitAllowingStateLoss()
    }

    protected open fun initView(savedInstanceState: Bundle?) {
        val fragment = getFragment()
        if (fragment != null) {
            openFragment(fragment, false)
        }
    }

    //protected open fun getLayout() = R.layout.fragment_main_list

    protected open fun getContainerResId() = R.id.container

    fun addBackToActionBar() {
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
    }

    /*override fun openDetailItemScreen(item: ItemList) {

    }*/


    fun getFragment(): Fragment? {
        return MainListFragment()
    }

    //IActivity
    override fun showError(errorMessage: String) {

    }

    override fun showError(errorMessageResId: Int) {

    }

    override fun showSuccess(messageResId: Int) {

    }

    override fun hideKeyboard() {

    }
}