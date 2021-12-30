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
import com.example.testapiflickr.data.model.SearchModel
import com.example.testapiflickr.databinding.FragmentSearchFlickrBinding
import com.example.testapiflickr.ui.view.adapter.ListSearchFlickAdapter
import com.example.testapiflickr.ui.view.fragments.listener.OnClickListFlickrListener
import com.example.testapiflickr.ui.viewmodel.SearchViewModel
import com.example.testapiflickr.utils.Utils

class SearchFlickrFragment : Fragment() {
    private var _binding: FragmentSearchFlickrBinding? = null
    private val binding get() = _binding!!
    private val searchViewModel: SearchViewModel by viewModels()
    private val itemsPhoto = mutableListOf<ItemPhoto>()
    private lateinit var adapterSearchFlickr: ListSearchFlickAdapter
    lateinit var listener: OnClickListFlickrListener
    private var count: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchFlickrBinding.inflate(inflater, container, false)
        setListener()
        loadDataSearch()
        initText()
        binding.tagSearchView.setOnQueryTextListener(configureSearch())
        return binding.root
    }

    private fun initText() {
        if (count == 0) {
            binding.initTextView.visibility = View.VISIBLE
            binding.initTextView.text = "Hi, look for photos on Flickr!"
            count++
        } else {
            binding.initTextView.visibility = View.GONE
        }
    }

    private fun loadDataSearch() {
        searchViewModel.mutableSearch.observe(
            viewLifecycleOwner,
            Observer { Result ->
                binding.loadingSearch.visibility = View.GONE
                loadData(Result)
            })
    }

    private fun loadData(data: SearchModel) {
        if (data.photos?.photo?.isNotEmpty() == true) {
            val listPhoto = data.photos.photo
            binding.listSearchRecyclerView.visibility = View.VISIBLE
            itemsPhoto.clear()
            itemsPhoto.addAll(listPhoto)
            initRecyclerView()
            adapterSearchFlickr.notifyDataSetChanged()
        } else {
            if (data.photos?.photo?.isEmpty() == true) {
                errorText("No results found!")
            } else {
                errorText("An error has occurred!")
            }
        }
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
                    binding.warningTextView.visibility = View.GONE
                    binding.initTextView.visibility = View.GONE
                    binding.listSearchRecyclerView.visibility = View.GONE
                    binding.loadingSearch.visibility = View.VISIBLE
                    if (context?.let { Utils.isNetworkAvailable(it) } == true) {
                        searchViewModel.onCreate(p0)
                    } else {
                        binding.loadingSearch.visibility = View.GONE
                        errorText("no connection, please activate")
                    }
                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        }
    }

    private fun errorText(setText: String) {
        binding.warningTextView.visibility = View.VISIBLE
        binding.warningTextView.text = setText
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