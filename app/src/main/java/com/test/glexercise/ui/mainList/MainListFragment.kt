package com.test.glexercise.ui.mainList

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.test.glexercise.DEFAULT_INT_VALUE
import com.test.glexercise.R
import com.test.glexercise.domain.model.ItemList
import com.test.glexercise.ui.base.IActivity
import com.test.glexercise.ui.detailList.DetailListActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainListFragment : Fragment(), OnMainListClickListener, SwipeRefreshLayout.OnRefreshListener {

    lateinit var callback: IActivity

    private lateinit var listMain: List<ItemList>
    //private val viewModelList by viewModels<MainListViewModel>()

    private val adapterMainList: MainListAdapter = MainListAdapter(this)

    private lateinit var binding: com.test.glexercise.databinding.FragmentMainListBinding

    fun getLayout(): Int = R.layout.fragment_main_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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

        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.divider_list)!!)
        binding.mainList.addItemDecoration(dividerItemDecoration)
        binding.mainListSwipeRefresh.setOnRefreshListener(this)

        callback.getMainListViewModel().getMainList()
        callback.getMainListViewModel().listData.observe(viewLifecycleOwner, {
            if (it != null) {
                listMain = it.data!!.toList()
                this.setMainList(listMain)
            }
        })
        callback.getMainListViewModel().refreshing.observe(viewLifecycleOwner, {
            binding.mainListSwipeRefresh.isRefreshing = it!!
        })
        callback.getMainListViewModel().showProgress.observe(viewLifecycleOwner, {
            binding.mainListProgressBar.visibility = if (it) (View.VISIBLE) else (View.GONE)
        })
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = context as IActivity
    }

    private fun setMainList(list: List<ItemList>) {
        binding.mainList.adapter = adapterMainList
        adapterMainList.updateMainList(list)

    }

    override fun openDetailItemScreen(item: ItemList) {
        goTo(DetailListActivity.getIntent(requireContext(), item), false)
    }

    override fun onRefresh() {
        callback.getMainListViewModel().getMainList()
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        if (hasMenu()) {
            inflater.inflate(getMenuResId(), menu)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    open fun getMenuResId(): Int {
        return DEFAULT_INT_VALUE
    }

    private fun hasMenu(): Boolean {
        return getMenuResId() != DEFAULT_INT_VALUE
    }
}