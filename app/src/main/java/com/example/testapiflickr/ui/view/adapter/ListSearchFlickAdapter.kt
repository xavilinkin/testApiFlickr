package com.example.testapiflickr.ui.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testapiflickr.data.model.ItemPhoto
import com.example.testapiflickr.databinding.ItemPhotoBinding

class ListSearchFlickAdapter(private val listSearch: List<ItemPhoto>) :
    RecyclerView.Adapter<ListSearchFlickAdapter.ListSearchHolder>() {
    private lateinit var binding: ItemPhotoBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSearchHolder {
        binding = ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListSearchHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ListSearchHolder, position: Int) {
        val item = listSearch[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = listSearch.size

    inner class ListSearchHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(itemPhoto: ItemPhoto) {
            binding.namePhotoTextView.text = itemPhoto.title
        }
    }
}