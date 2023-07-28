package com.example.mvvmclean.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmclean.databinding.ActivityMainBinding
import com.example.mvvmclean.model.Photo
import com.example.mvvmclean.view.adapter.ImageAdapter
import com.example.mvvmclean.viewmodel.ImageViewModel

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val imageAdapter by lazy { ImageAdapter(this, imageList) }
    private var imageList = ArrayList<Photo>()
    private lateinit var imageViewModel: ImageViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        binding.imageRv.adapter = imageAdapter
        binding.imageRv.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        this code sets up the ImageViewModel to observe changes to the photoList LiveData,
//        and when you call imageViewModel.fetchPhotos(), it fetches the list of photos and
//        updates the UI accordingly. This ensures that the UI stays in sync with the data in the
//        ImageViewModel, and any changes to the list of photos will be automatically reflected
//        in the RecyclerView through the imageAdapter
        imageViewModel = ViewModelProvider(this)[ImageViewModel::class.java]
        imageViewModel.photoList.observe(this) { images ->
            imageAdapter.update(images)
        }
        imageViewModel.fetchPhotos()
    }

}