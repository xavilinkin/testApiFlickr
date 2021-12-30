package com.example.testapiflickr.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.testapiflickr.constants.Constants
import com.example.testapiflickr.databinding.FragmentDetailFlickBinding
import com.squareup.picasso.Picasso

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
        binding.textBack.text = "Volver"
        binding.textBack.setOnClickListener {
            parentFragment?.findNavController()?.popBackStack()
        }
        setDataImage()
        setText()
        return binding.root
    }

    private fun setText() {
        binding.authorTextView.text = "Autor: F. Alonso"
        binding.titleTextView.text = "Título: Fórmula 1"
        binding.dateTextView.text = "Fecha: 18/09/1939"
        binding.descriptionTextView.text = "Descripción: No description"
    }

    private fun setDataImage() {
        val formatImage = args.urlPhoto + Constants.BIG_IMAGE
        Picasso.get().load(formatImage).into(binding.imageFlickrImageView)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}