package com.ljh.databinding.data.network.api

import retrofit2.Call
import retrofit2.http.GET

interface WeatherService {
    @GET("api/bing_pic")
    fun getBingPic(): Call<String>
}