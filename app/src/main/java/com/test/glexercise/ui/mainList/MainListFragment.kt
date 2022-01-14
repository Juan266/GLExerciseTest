package com.test.glexercise.ui.mainList

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.test.glexercise.R
import com.test.glexercise.domain.model.ItemList
import com.test.glexercise.ui.base.BaseFragment
import com.test.glexercise.ui.detailList.DetailListActivity
import dagger.hilt.android.AndroidEntryPoint


//@AndroidEntryPoint
class MainListFragment : Fragment(), OnMainListClickListener, SwipeRefreshLayout.OnRefreshListener {

    private lateinit var listMain: List<ItemList>
    //private val viewModelList : MainListViewModel by viewModel()
    private val viewModelList by viewModels<MainListViewModel>()


    private val adapterMainList: MainListAdapter = MainListAdapter(this)

    private lateinit var binding: com.test.glexercise.databinding.FragmentMainListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }



    fun getLayout(): Int = R.layout.fragment_main_list

    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, getLayout(), container, false)

        /*binding.mainList.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        val dividerItemDecoration = DividerItemDecoration(
            context,
            LinearLayoutManager.VERTICAL
        )

        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.divider_list)!!)
        binding.mainList.addItemDecoration(dividerItemDecoration)
        binding.mainListSwipeRefresh.setOnRefreshListener(this)*/

        viewModelList.getMainList()
        viewModelList.listData.observe(viewLifecycleOwner, {
            if (it != null) {
                //listMain = it.asList()
                //setMainList(listMain)
            }
        })
        viewModelList.refreshing.observe(viewLifecycleOwner, {
            //binding.mainListSwipeRefresh.isRefreshing = it!!
        })
        viewModelList.showProgress.observe(viewLifecycleOwner, {
            //binding.mainListProgressBar.visibility = if (it) (View.VISIBLE) else (View.GONE)
        })
        return binding.root
    }

    private fun setMainList(list: List<ItemList>) {
        //binding.mainList.adapter = adapterMainList
        adapterMainList.updateMainList(list)

    }

    override fun openDetailItemScreen(item: ItemList) {
        goTo(DetailListActivity.getIntent(requireContext(), item), false)
    }



    override fun onRefresh() {
        this.viewModelList.getMainList()
    }

    fun goTo(intent: Intent, finishActivity: Boolean) {
        if (activity != null && isAdded) {
            goToIntent(intent, finishActivity, false)
        }
    }

    private fun goToIntent(intent: Intent, finishActivity: Boolean, slideAnimation: Boolean) {
        startActivity(intent)
        if (finishActivity) {
            requireActivity().finish()
        }
    }
}