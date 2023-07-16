package com.example.clear.networkServices

import com.example.clear.screens.home.dictionary.data.WordInfoDto
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Singleton


@Singleton
interface CommonApiServices {

    @GET("/api/v2/entries/en/{word}")
    suspend fun getWordDetails(
        @Path("word") word: String
    ) : List<WordInfoDto>



}