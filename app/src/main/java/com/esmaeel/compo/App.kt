package com.esmaeel.compo

import android.app.Application
import androidx.multidex.MultiDexApplication
import coil.Coil
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.util.CoilUtils
import okhttp3.OkHttpClient

class App : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        // initiating the coil library for caching and other stuff.
        Coil.setImageLoader {
            val coilOkHttpClient = OkHttpClient.Builder()
                .cache(CoilUtils.createDefaultCache(this@App))
                .build()

            ImageLoader.Builder(this@App)
                .okHttpClient(coilOkHttpClient)
                .build()
        }
    }
}