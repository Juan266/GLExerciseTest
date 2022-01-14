package com.test.glexercise.ui.base

import androidx.lifecycle.ViewModel
import com.test.glexercise.ui.mainList.MainListViewModel

interface IActivity {
    fun getMainListViewModel(): MainListViewModel
    fun showError(errorMessage: String)
    fun showError(errorMessageResId: Int)
    fun showSuccess(messageResId: Int)
    fun hideKeyboard()
}