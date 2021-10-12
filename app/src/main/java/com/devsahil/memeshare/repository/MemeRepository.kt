package com.devsahil.memeshare.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.devsahil.memeshare.api.MemeService
import com.devsahil.memeshare.models.RandomMeme

class MemeRepository (private val memeService: MemeService) {

    private val memeLiveData = MutableLiveData<RandomMeme>()

    val meme : LiveData<RandomMeme>
    get() = memeLiveData

    suspend fun getMeme(){
        val result = memeService.getMeme()
        if (result.body() != null){
            memeLiveData.postValue(result.body())
        }
    }
}