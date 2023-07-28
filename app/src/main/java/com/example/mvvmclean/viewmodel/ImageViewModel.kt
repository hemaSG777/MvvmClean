package com.example.mvvmclean.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmclean.model.ImageModel
import com.example.mvvmclean.model.Photo
import com.example.mvvmclean.utils.ImageApi
import retrofit2.Call
import retrofit2.Response

class ImageViewModel : ViewModel() {
    private val _photoList = MutableLiveData<List<Photo>>()
    val photoList: LiveData<List<Photo>> = _photoList

    // when you call the fetchPhotos() method,
    // it fetches the list of photos from the data source and then updates the _photoList LiveData
    // with the new data. Any UI component observing photoList will receive the updated list of photos
    // and can update the UI accordingly.
    fun fetchPhotos() {
        val apiInterface = ImageApi.imageList().getImageList()
        apiInterface.enqueue(object : retrofit2.Callback<ImageModel> {
            override fun onResponse(call: Call<ImageModel>, response: Response<ImageModel>) {
                val res = response.body()?.photos?.photo
                _photoList.value = res
            }

            override fun onFailure(call: Call<ImageModel>, t: Throwable) {

            }

        })
    }

}

