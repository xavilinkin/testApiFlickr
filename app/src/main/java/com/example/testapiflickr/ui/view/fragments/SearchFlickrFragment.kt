package com.example.testapiflickr.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.testapiflickr.databinding.FragmentSearchFlickrBinding

class SearchFlickrFragment : Fragment() {
    private var _binding: FragmentSearchFlickrBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchFlickrBinding.inflate(inflater, container, false)
        binding.testClick.setOnClickListener {
            testNavigation()
        }
        return binding.root
    }

    private fun testNavigation(){
        val passArgs = SearchFlickrFragmentDirections.actionSearchFlickrFragmentToDetailFlickFragment("12345", "https://url.invent")
        findNavController().navigate(passArgs)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}