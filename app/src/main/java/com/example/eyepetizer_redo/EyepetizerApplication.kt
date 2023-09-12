package com.example.eyepetizer_redo

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class EyepetizerApplication:Application() {
    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }
}
