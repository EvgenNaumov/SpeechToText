package com.naumov

import android.app.Application
import android.content.Context
import android.content.res.Resources

class App:Application() {

    companion object{
        private var mContext: Context? = null

        fun getContext(): Context {
            return mContext!!
        }

        fun getResources(): Resources = mContext!!.resources
    }

    override fun onCreate() {

        super.onCreate()
        mContext = applicationContext

    }
}