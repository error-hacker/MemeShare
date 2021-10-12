package com.devsahil.memeshare

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.devsahil.memeshare.api.MemeService
import com.devsahil.memeshare.api.RetrofitHelper
import com.devsahil.memeshare.repository.MemeRepository
import com.devsahil.memeshare.viewmodels.MainViewModel
import com.devsahil.memeshare.viewmodels.MainViewModelFactory
import com.bumptech.glide.Glide

import androidx.databinding.DataBindingUtil
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.devsahil.memeshare.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var mainViewModel: MainViewModel
    private var url: String?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)

        val memeService = RetrofitHelper.getInstance().create(MemeService::class.java)
        val repository = MemeRepository(memeService)

        mainViewModel = ViewModelProvider(this,MainViewModelFactory(repository)).get(MainViewModel::class.java)

        mainViewModel.meme.observe(this, Observer {
            url = it.url
            Log.d("chechshek", url!!)
            if(!it.nsfw) {
                Glide.with(this).load(url).into(binding.memeImageView)
            }
            else{
                mainViewModel.nextMeme()
            }

        })


        binding.mainViewModel = mainViewModel

        binding.shareButton.setOnClickListener{
            mainViewModel.shareMeme(it, url)
        }


    }

    fun onClickShare(view: android.view.View) {

    }
}