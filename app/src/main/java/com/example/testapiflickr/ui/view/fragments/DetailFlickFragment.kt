package com.example.testapiflickr.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.testapiflickr.databinding.FragmentDetailFlickBinding

class DetailFlickFragment : Fragment() {
    private var _binding: FragmentDetailFlickBinding? = null
    private val binding get() = _binding!!
    private val args: DetailFlickFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailFlickBinding.inflate(inflater, container, false)
        binding.testArgs.text = args.idPhoto + args.urlPhoto
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}