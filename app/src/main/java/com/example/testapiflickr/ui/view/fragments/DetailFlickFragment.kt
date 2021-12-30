package com.example.testapiflickr.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.testapiflickr.constants.Constants
import com.example.testapiflickr.data.model.PhotoInfoModel
import com.example.testapiflickr.databinding.FragmentDetailFlickBinding
import com.example.testapiflickr.ui.viewmodel.DetailViewModel
import com.squareup.picasso.Picasso

class DetailFlickFragment : Fragment() {
    private var _binding: FragmentDetailFlickBinding? = null
    private val binding get() = _binding!!
    private val args: DetailFlickFragmentArgs by navArgs()
    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailViewModel.onCreate(args.idPhoto)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailFlickBinding.inflate(inflater, container, false)
        binding.loadingDetail.visibility = View.VISIBLE
        binding.textBack.text = "Back"
        binding.textBack.setOnClickListener {
            parentFragment?.findNavController()?.popBackStack()
        }
        loadDataInfo()
        return binding.root
    }

    private fun loadDataInfo() {
        detailViewModel.mutableInfoPhoto.observe(viewLifecycleOwner, Observer { InfoPhoto ->
            binding.loadingDetail.visibility = View.GONE
            setDataImage()
            setText(InfoPhoto)
        })
    }

    private fun setText(info: PhotoInfoModel) {
        binding.authorTextView.text = "Author: " + controlText(info.photo?.owner?.username)
        binding.titleTextView.text = "Title: " + controlText(info.photo?.title?._content)
        binding.dateTextView.text = "Date: " + controlText(info.photo?.dates?.taken, true)
        binding.descriptionTextView.text =
            "Description: " + controlText(info.photo?.description?._content)
    }

    private fun controlText(data: String?, isDate: Boolean = false): String {
        return if (data.isNullOrBlank()) {
            "no data"
        } else {
            if (isDate) {
                data.substring(0, 10)
            } else {
                data
            }
        }
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