package com.test.glexercise.ui.mainList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.glexercise.domain.model.ItemList
import com.test.glexercise.domain.repository.MainListRepository
import com.test.glexercise.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class MainListViewModel @Inject constructor(private val mainListRepository: MainListRepository) : ViewModel() {

    private val _listData: MutableLiveData<Array<ItemList>> = MutableLiveData()
    val listData: LiveData<Array<ItemList>> = _listData

    private val _refreshing: MutableLiveData<Boolean> = MutableLiveData()
    val refreshing: LiveData<Boolean> = _refreshing

    private val _showProgress: MutableLiveData<Boolean> = MutableLiveData()
    val showProgress: LiveData<Boolean> = _showProgress

    private val _errorList: MutableLiveData<String> = MutableLiveData()

    private var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _errorList.value = "Error" + throwable.localizedMessage
    }

    init {
        _refreshing.value = false
        _showProgress.value = true
    }


    fun getMainList() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = mainListRepository.getList()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    onGetMainListSuccess(response.body()!!)
                } else {
                    onGetMainListError("Error : ${response.message()} ")
                }
            }
        }
    }

    private fun onGetMainListSuccess(result: Array<ItemList>) {
        _refreshing.value = false
        _showProgress.value = false
        _listData.value = result


    }

    private fun onGetMainListError(error: String) {
        _errorList.value = error
    }



}