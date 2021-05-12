package com.jetbrains.handson.mpp.sentiaandroidcodechallenge.ui.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import domain.Property

class PropertyDetailViewModel(property:Property): ViewModel() {

    private val _selectedProperty = MutableLiveData<Property>()
    val selectedProperty: LiveData<Property>
        get() = _selectedProperty
    init {
        _selectedProperty.value = property
    }
}

class DetailViewModelFactory(private val property: Property) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PropertyDetailViewModel::class.java)) {
            return PropertyDetailViewModel(property) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}