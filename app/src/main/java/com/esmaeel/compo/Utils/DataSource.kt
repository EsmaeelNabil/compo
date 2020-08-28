package com.esmaeel.composepalygroundtwo

import com.esmaeel.compo.data.models.PopularPersonsResponse
import com.google.gson.Gson
import com.orhanobut.logger.BuildConfig
import com.orhanobut.logger.Logger
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesNetworkService {

    @GET("person/popular")
    suspend fun getPopularPersons(@Query("page") pageNumber: Int): Response<PopularPersonsResponse>

//    @GET("person/{person_id}/images")
//    suspend fun getPersonImages(@Path("person_id") personId: Int?): Response<PersonsImagesResponse>

}

val baseUrl = "https://api.themoviedb.org/3/"
val apiKey = "0a4b834208c248be5e926aa56b23e6da"

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

//fun provideLoggingInterceptor(): HttpLoggingInterceptor {
//    return if (BuildConfig.DEBUG) HttpLoggingInterceptor(object :
//        HttpLoggingInterceptor.Logger {
//        override fun log(message: String) {
//            if (message.startsWith("{") || message.startsWith("["))
//                Logger.t("Constants.JR").json(message)
//            else Logger.t("Constants.OK_HTTP_MESSAGE_LOGGER").i(message)
//        }
//
//    }).setLevel(
//        HttpLoggingInterceptor.Level.BODY
//    ) else HttpLoggingInterceptor().setLevel(
//        HttpLoggingInterceptor.Level.NONE
//    )
//}

fun getOk(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(keyInjector())
//        .addInterceptor(provideLoggingInterceptor())
        .build()
}

fun getService(): MoviesNetworkService {
    return Retrofit.Builder()
        .client(getOk())
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        /*.addCallAdapterFactory(RxJava2CallAdapterFactory.create())*/
        .baseUrl(baseUrl)
        .build()
        .create(MoviesNetworkService::class.java)
}