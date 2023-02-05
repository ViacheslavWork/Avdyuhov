package com.example.avdyuhov.data.remote

import com.example.avdyuhov.data.remote.dto.DetailsResponse
import com.example.avdyuhov.data.remote.dto.ItemListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Viacheslav Avd on 03.02.2023
 */
interface ApiService {
    @GET("top")
    suspend fun getPopularMovies(
        @Query("type") type: String = "TOP_100_POPULAR_FILMS",
        @Query("page") page: Int = 1
    ): ItemListResponse

    @GET("{movie_id}")
    suspend fun getMovie(@Path("movie_id") movie_id: Int): DetailsResponse
}