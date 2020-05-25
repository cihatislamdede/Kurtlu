package com.cihatislamdede.kurtlu

import android.app.Application
import com.parse.Parse

class StarterApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG)

        Parse.initialize(Parse.Configuration.Builder(this)
            .applicationId("YOUR_API_KEY")
            .clientKey("YOUT_CLIENT_KEY")
            .server("https://parseapi.back4app.com/")
            .build())

    }
}