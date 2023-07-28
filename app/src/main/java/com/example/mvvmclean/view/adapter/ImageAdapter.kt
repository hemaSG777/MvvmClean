package com.example.mvvmclean.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmclean.databinding.LayoutImageBinding
import com.example.mvvmclean.model.Photo

class ImageAdapter(val context: Context,private var imageList: List<Photo>) : RecyclerView.Adapter<ImageAdapter.MyViewHolder>() {



    inner class MyViewHolder(val binding: LayoutImageBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = imageList[position]
        with(holder) {
            binding.apply {
                Glide.with(context)
                    .load(item.url_s)
                    .into(image)
                nameTv.text = item.title
            }
        }
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    fun update(newList: List<Photo>) {
        imageList = newList
        notifyDataSetChanged()
    }


}