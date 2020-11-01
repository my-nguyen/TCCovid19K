package com.nguyen.tccovid19k

import android.app.Application

class MyApplication : Application() {
    val appComponent = DaggerApplicationComponent.create()
}