package com.example.testapiflickr.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.testapiflickr.data.model.SearchModel
import com.example.testapiflickr.databinding.FragmentSearchFlickrBinding
import com.example.testapiflickr.ui.viewmodel.SearchViewModel

class SearchFlickrFragment : Fragment() {
    private var _binding: FragmentSearchFlickrBinding? = null
    private val binding get() = _binding!!
    private val searchViewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchFlickrBinding.inflate(inflater, container, false)
        binding.testClick.setOnClickListener {
            testNavigation()
        }
        loadDataSearch()
        binding.tagSearchView.setOnQueryTextListener(configureSearch())
        return binding.root
    }

    private fun loadDataSearch() {
        searchViewModel.mutableSearch.observe(
            viewLifecycleOwner,
            Observer { Result ->
                testResult(Result)
            })
    }

    private fun testResult(test: SearchModel?) {
        val prueba = test
    }

    private fun configureSearch(): androidx.appcompat.widget.SearchView.OnQueryTextListener {
        return object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                if (!p0.isNullOrBlank()) {
                    searchViewModel.onCreate(p0)
                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun testNavigation() {
        val passArgs =
            SearchFlickrFragmentDirections.actionSearchFlickrFragmentToDetailFlickFragment(
                "12345",
                "https://url.invent"
            )
        findNavController().navigate(passArgs)
    }
}