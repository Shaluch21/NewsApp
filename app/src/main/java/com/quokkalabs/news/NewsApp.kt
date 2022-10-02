package com.quokkalabs.news

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

/*
Application Entry point
*/
@HiltAndroidApp
class NewsApp : Application() {

    var context: Context? = null

    val TAG = NewsApp::class.java.simpleName
    private var mInstance: NewsApp? = null

    override fun onCreate() {
        super.onCreate()
        mInstance = this
    }


}