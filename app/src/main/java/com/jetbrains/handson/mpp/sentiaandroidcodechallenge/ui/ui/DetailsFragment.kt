package com.jetbrains.handson.mpp.sentiaandroidcodechallenge.ui.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.jetbrains.handson.mpp.sentiaandroidcodechallenge.databinding.FragmentDetailsBinding
import com.jetbrains.handson.mpp.sentiaandroidcodechallenge.ui.viewmodels.DetailViewModelFactory
import com.jetbrains.handson.mpp.sentiaandroidcodechallenge.ui.viewmodels.PropertyDetailViewModel
import com.jetbrains.handson.mpp.sentiaandroidcodechallenge.ui.viewmodels.PropertyListViewModel

class DetailsFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentDetailsBinding.inflate(inflater)
        val property = DetailsFragmentArgs.fromBundle(requireArguments()).selectedProperty
        val viewModelFactory = DetailViewModelFactory(property)
        val viewModel = ViewModelProvider(
            this, viewModelFactory).get(PropertyDetailViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }
}

