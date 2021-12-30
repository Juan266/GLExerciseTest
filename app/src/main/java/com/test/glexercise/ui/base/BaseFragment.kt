package com.test.glexercise.ui.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.*


abstract class BaseFragment : Fragment() {
    lateinit var callback: IActivity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return layoutInflater.inflate(getLayout(), container, false)
    }

    protected abstract fun getLayout(): Int

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = context as IActivity
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
        return -1 //DEFAULT_INT_VALUE
    }

    private fun hasMenu(): Boolean {
        return getMenuResId() != -1 //DEFAULT_INT_VALUE
    }

    /*fun showError(textInputLayout: TextInputLayout, value: Int) {
        textInputLayout.error = requireContext().getString(value)
    }*/
}