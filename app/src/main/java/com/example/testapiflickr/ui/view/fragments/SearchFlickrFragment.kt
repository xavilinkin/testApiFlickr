package com.example.testapiflickr.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapiflickr.data.model.ItemPhoto
import com.example.testapiflickr.databinding.FragmentSearchFlickrBinding
import com.example.testapiflickr.ui.view.adapter.ListSearchFlickAdapter
import com.example.testapiflickr.ui.view.fragments.listener.OnClickListFlickrListener
import com.example.testapiflickr.ui.viewmodel.SearchViewModel

class SearchFlickrFragment : Fragment() {
    private var _binding: FragmentSearchFlickrBinding? = null
    private val binding get() = _binding!!
    private val searchViewModel: SearchViewModel by viewModels()
    private val itemsPhoto = mutableListOf<ItemPhoto>()
    lateinit var adapterSearchFlickr: ListSearchFlickAdapter
    lateinit var listener: OnClickListFlickrListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchFlickrBinding.inflate(inflater, container, false)
        setListener()
        initRecyclerView()
        loadDataSearch()
        binding.tagSearchView.setOnQueryTextListener(configureSearch())
        return binding.root
    }

    private fun loadDataSearch() {
        searchViewModel.mutableSearch.observe(
            viewLifecycleOwner,
            Observer { Result ->
                binding.listSearchRecyclerView.visibility = View.VISIBLE
                binding.loadingSearch.visibility = View.GONE
                val listPhoto = Result.photos?.photo ?: emptyList()
                itemsPhoto.clear()
                itemsPhoto.addAll(listPhoto)
                adapterSearchFlickr.notifyDataSetChanged()
            })
    }

    private fun initRecyclerView() {
        adapterSearchFlickr = ListSearchFlickAdapter(itemsPhoto, listener)
        binding.listSearchRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.listSearchRecyclerView.adapter = adapterSearchFlickr
    }

    private fun configureSearch(): androidx.appcompat.widget.SearchView.OnQueryTextListener {
        return object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                if (!p0.isNullOrBlank()) {
                    binding.listSearchRecyclerView.visibility = View.GONE
                    binding.loadingSearch.visibility = View.VISIBLE
                    searchViewModel.onCreate(p0)
                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        }
    }

    private fun setListener() {
        listener = object : OnClickListFlickrListener {
            override fun goToImage(idPhoto: String, urlImage: String) {
                val passArgs =
                    SearchFlickrFragmentDirections.actionSearchFlickrFragmentToDetailFlickFragment(
                        idPhoto,
                        urlImage
                    )
                findNavController().navigate(passArgs)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}