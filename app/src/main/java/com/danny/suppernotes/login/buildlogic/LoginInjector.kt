package com.danny.suppernotes.login.buildlogic

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.danny.suppernotes.login.LoginActivity
import com.danny.suppernotes.login.LoginLogic

class LoginInjector(application: Application) : AndroidViewModel(application) {
    init {
        FirebaseApp.initializeApp(application)
    }

    //For user management
    private val auth: IAuthRepository by lazy {
        //by using lazy, I don't load this resource until I need it
        FirebaseAuthRepositoryImpl()
    }


    fun buildLoginLogic(view: LoginActivity): LoginLogic = LoginLogic(
        DispatcherProvider,
        UserServiceLocator(auth),
        view,
        AuthSource()
    ).also { view.setObserver(it) }
}