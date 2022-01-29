package com.appcrafters.wallpaperapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.appcrafters.retrofitapigg.MainViewModel
import com.appcrafters.retrofitapigg.MainViewModelFactory
import com.appcrafters.retrofitapigg.base.models.Photo
import com.appcrafters.retrofitapigg.base.recycler.PhotosRVAdapter
import com.appcrafters.retrofitapigg.base.repository.Repository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), (Photo) -> Unit {

    private lateinit var viewModel: MainViewModel
    private val photosList: List<Photo> = ArrayList()
    private val photoAdapter by lazy { PhotosRVAdapter(photosList, this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        viewModel.getPhotos()
        viewModel.photoResponse.observe(this, Observer { response ->
            if(response.isSuccessful) {
                response.body()?.let {photoAdapter.setData(it)}
            } else {
                Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun setupRecyclerView() {
        photoslistRV.adapter = photoAdapter
        photoslistRV.layoutManager = GridLayoutManager(this, 2)
    }

    override fun invoke(photo: Photo) {
        Intent(this, WallpaperActivity::class.java).also {
            it.putExtra("photo", photo)
            startActivity(it)
        }
    }
}