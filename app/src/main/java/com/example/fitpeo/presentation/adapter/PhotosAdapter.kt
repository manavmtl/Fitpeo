package com.example.fitpeo.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fitpeo.R
import com.example.fitpeo.databinding.ItemPhotosBinding
import com.example.fitpeo.domain.PhotosResponseItem
import com.example.fitpeo.presentation.utils.nul
import com.squareup.picasso.Picasso

class PhotosAdapter(private val onItemClicked: (position:Int,item: PhotosResponseItem) -> Unit) :
    RecyclerView.Adapter<PhotosAdapter.MyViewHolder>() {

    private var photosList: ArrayList<PhotosResponseItem> = ArrayList()

    inner class MyViewHolder(private val binding: ItemPhotosBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int, item: PhotosResponseItem) {
            binding.apply {
                Picasso.get()
                    .load(item.thumbnailUrl.nul())
                    .placeholder(R.mipmap.ic_launcher)
                    .into(ivThumbnail)

                tvTitle.text=item.title.nul()

                root.setOnClickListener {
                    onItemClicked(position,item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemPhotosBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = photosList.size

    override fun getItemId(position: Int) = position.toLong()

    override fun getItemViewType(position: Int) = position

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = photosList[position]
        holder.bind(position, item)
    }

    fun updateList(photosList: ArrayList<PhotosResponseItem>) {
        this.photosList = photosList
        notifyDataSetChanged()
    }
}