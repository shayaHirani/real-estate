package com.jetbrains.handson.mpp.sentiaandroidcodechallenge.ui.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.jetbrains.handson.mpp.sentiaandroidcodechallenge.PropertiesAdapter
import com.jetbrains.handson.mpp.sentiaandroidcodechallenge.databinding.FragmentDetailsBinding
import com.jetbrains.handson.mpp.sentiaandroidcodechallenge.databinding.FragmentPropertyListBinding
import com.jetbrains.handson.mpp.sentiaandroidcodechallenge.databinding.PropertyListItemBinding
import com.jetbrains.handson.mpp.sentiaandroidcodechallenge.ui.viewmodels.PropertyListViewModel
import com.jetbrains.handson.mpp.sentiaandroidcodechallenge.ui.viewmodels.PropertyListViewModelFactory

class PropertyListFragment : Fragment() {

    private lateinit var viewModel: PropertyListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentPropertyListBinding.inflate(inflater)
        val application = requireNotNull(activity).application
        val viewModelFactory = PropertyListViewModelFactory(application)
        val viewModel = ViewModelProvider(
            this, viewModelFactory).get(PropertyListViewModel::class.java)

        binding.propertiesRecyclerView.adapter = PropertiesAdapter(PropertiesAdapter.OnClickListener {
            viewModel.displayProperty(it)
        })

        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
            if ( null != it ) {
                this.findNavController().navigate(PropertyListFragmentDirections.actionPropertyListFragmentToDetailsFragment(it))
                viewModel.displayPropertyComplete()
            }
        })

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

}