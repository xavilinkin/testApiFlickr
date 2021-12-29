package com.example.testapiflickr.ui.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testapiflickr.constants.Constants.HOSTNAME_PHOTOS
import com.example.testapiflickr.constants.Constants.SMALL_IMAGE
import com.example.testapiflickr.data.model.ItemPhoto
import com.example.testapiflickr.databinding.ItemPhotoBinding
import com.example.testapiflickr.ui.view.fragments.listener.OnClickListFlickrListener
import com.squareup.picasso.Picasso

class ListSearchFlickAdapter(
    private val listSearch: List<ItemPhoto>,
    private var listener: OnClickListFlickrListener
) :
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
            val title = if (itemPhoto.title.isBlank()) {
                "not title"
            } else {
                itemPhoto.title
            }
            binding.namePhotoTextView.text = title
            val image = HOSTNAME_PHOTOS + itemPhoto.server + "/" +
                    itemPhoto.id + "_" + itemPhoto.secret
            val formatImage = image + SMALL_IMAGE
            Picasso.get().load(formatImage).into(binding.imageImageView)
            binding.nameItemCardView.setOnClickListener {
                listener.goToImage(itemPhoto.id, image)
            }
        }
    }
}