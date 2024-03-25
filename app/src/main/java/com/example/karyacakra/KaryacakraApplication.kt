package com.example.karyacakra

import android.app.Application
import com.example.karyacakra.data.AppContainer
import com.example.karyacakra.data.DefaultAppContainer
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.FirebaseApp

class KaryacakraApplication: Application() {

    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }
}