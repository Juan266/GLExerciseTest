package com.test.glexercise.ui.mainList

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.test.glexercise.R
import com.test.glexercise.domain.model.ItemList
import com.test.glexercise.ui.base.BaseFragment
import com.test.glexercise.ui.detailList.DetailListActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainListFragment : BaseFragment(), OnMainListClickListener, SwipeRefreshLayout.OnRefreshListener {

    private lateinit var listMain: List<ItemList>
    private val viewModelList : MainListViewModel by viewModel()
    private val adapterMainList: MainListAdapter = MainListAdapter(this)

    private lateinit var binding: com.test.glexercise.databinding.FragmentMainListBinding

    override fun getLayout(): Int = R.layout.fragment_main_list

    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, getLayout(), container, false)

        binding.mainList.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        val dividerItemDecoration = DividerItemDecoration(
            context,
            LinearLayoutManager.VERTICAL
        )
        dividerItemDecoration.setDrawable(requireContext().getDrawable(R.drawable.divider_list)!!)
        binding.mainList.addItemDecoration(dividerItemDecoration)
        binding.mainListSwipeRefresh.setOnRefreshListener(this)

        viewModelList.getMainList()
        viewModelList.listData.observe(viewLifecycleOwner, {
            if (it != null) {
                listMain = it.asList()
                setMainList(listMain)
            }
        })
        viewModelList.refreshing.observe(viewLifecycleOwner, {
            binding.mainListSwipeRefresh.isRefreshing = it!!
        })
        viewModelList.showProgress.observe(viewLifecycleOwner, {
            binding.mainListProgressBar.visibility = if (it) (View.VISIBLE) else (View.GONE)
        })
        return binding.root
    }

    private fun setMainList(list: List<ItemList>) {
        binding.mainList.adapter = adapterMainList
        adapterMainList.updateMainList(list)

    }

    override fun openDetailItemScreen(item: ItemList) {
        goTo(DetailListActivity.getIntent(requireContext(), item), false)
    }

    override fun onRefresh() {
        this.viewModelList.getMainList()
    }
}