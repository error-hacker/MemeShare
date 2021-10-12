package com.devsahil.memeshare.api

import com.devsahil.memeshare.models.RandomMeme
import retrofit2.Response
import retrofit2.http.GET

interface MemeService {

    @GET("/gimme")
    suspend fun getMeme() : Response<RandomMeme>

    //baseurl + "/gimme"
}