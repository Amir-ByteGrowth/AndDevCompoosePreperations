package com.example.cranecodelabclone

import android.app.Application
import com.example.cranecodelabclone.util.UnsplashSizingInterceptor
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.compose.AsyncImage
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication :Application(), ImageLoaderFactory {

    /**
     * Create the singleton [ImageLoader].
     * This is used by [AsyncImage] to load images in the app.
     */

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .components {
                add(UnsplashSizingInterceptor)
            }
            .build()
    }
}