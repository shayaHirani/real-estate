package com.jetbrains.handson.mpp.sentiaandroidcodechallenge.ui.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jetbrains.handson.mpp.sentiaandroidcodechallenge.framework.network.NetWorkApi
import com.jetbrains.handson.mpp.sentiaandroidcodechallenge.framework.network.NetworkResponse
import com.jetbrains.handson.mpp.sentiaandroidcodechallenge.framework.network.asDomainModel
import domain.Property
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await

enum class ApiStatus { LOADING, ERROR, DONE }

class PropertyListViewModel(application: Application) : ViewModel() {

    private val _navigateToSelectedProperty = MutableLiveData<Property>()
    val navigateToSelectedProperty: LiveData<Property>
        get() = _navigateToSelectedProperty

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    private val _properties = MutableLiveData<List<Property>>()
    val properties: LiveData<List<Property>>
        get() = _properties


    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    init {
        _navigateToSelectedProperty.value = null
        getProperty()

    }

    private fun getProperty() {
        coroutineScope.launch {
              try {
                _status.value = ApiStatus.LOADING
                val listResult = NetWorkApi
                    .retrofitService
                    .getProperties()
                    .await().
                    asDomainModel()
                _status.value = ApiStatus.DONE
                _properties.value = listResult
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
                _properties.value = ArrayList()
            }
        }
    }

    fun displayProperty(selectedProperty:Property) {
        _navigateToSelectedProperty.value = selectedProperty
    }

    fun displayPropertyComplete() {
        _navigateToSelectedProperty.value = null
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}

class PropertyListViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PropertyListViewModel::class.java)) {
            return PropertyListViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}