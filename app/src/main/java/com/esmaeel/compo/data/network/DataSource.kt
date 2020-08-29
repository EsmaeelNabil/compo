package com.esmaeel.compo.data.network

import com.esmaeel.compo.data.models.PopularPersonsResponse
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesNetworkService {

    @GET("person/popular")
    suspend fun getPopularPersons(@Query("page") pageNumber: Int): Response<PopularPersonsResponse>

}

val baseUrl = "https://api.themoviedb.org/3/"
val apiKey = "0a4b834208c248be5e926aa56b23e6da"

/**
 *  this interceptor injects the apiKey in every request.
 */
fun keyInjector(): Interceptor {
    return Interceptor { chain: Interceptor.Chain ->
        val original = chain.request()
        chain.proceed(
            chain.request().newBuilder()
                .url(
                    original.url.newBuilder()
                        .addQueryParameter("api_key", apiKey)
                        .build()
                )
                .method(original.method, original.body)
                .build()
        )
    }
}

fun getOk(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(keyInjector())
        .build()
}

fun getService(): MoviesNetworkService {
    return Retrofit.Builder()
        .client(getOk())
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .baseUrl(baseUrl)
        .build()
        .create(MoviesNetworkService::class.java)
}