package com.danny.suppernotes.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.danny.suppernotes.R
import com.danny.suppernotes.login.buildlogic.LoginInjector
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), ILoginContract.View {
    override fun setObserver(observer: Observer<LoginEvent<LoginResult>>) = event.observeForever(observer)

    override fun startListFeature() = com.wiseassblog.spacenotes.common.startListFeature(this)

    val event = MutableLiveData<LoginEvent<LoginResult>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Note: I cal setObserver within the LoginInjector function
        ViewModelProviders.of(this)
            .get(LoginInjector::class.java)
            .buildLoginLogic(this)

        btn_auth_attempt.setOnClickListener { event.value = LoginEvent.OnAuthButtonClick }
        imb_toolbar_back.setOnClickListener { event.value = LoginEvent.OnBackClick }
    }


    override fun onResume() {
        super.onResume()
        event.value = LoginEvent.OnStart
    }

    override fun setLoginStatus(text: String) {
        lbl_login_status_display.text = text
    }

    override fun setAuthButton(text: String) {
        btn_auth_attempt.text = text
    }

    override fun showLoopAnimation() {
        imv_antenna_animation.setImageResource(
            resources.getIdentifier("antenna_loop_fast", "drawable", this.packageName)
        )

        val satelliteLoop = imv_antenna_animation.drawable as AnimationDrawable
        satelliteLoop.start()
    }

    override fun setStatusDrawable(imageURL: String) {
        imv_antenna_animation.setImageResource(
            resources.getIdentifier(imageURL, "drawable", this.packageName)
        )

    }

    override fun startSignInFlow() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .build()

        val googleSignInClient = GoogleSignIn.getClient(this, gso)

        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {

            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                val account: GoogleSignInAccount? = task.getResult(ApiException::class.java)

                event.value = LoginEvent.OnGoogleSignInResult(
                    LoginResult(
                        requestCode,
                        account
                    )
                )

            } catch (exception: Exception) {
                event.value = LoginEvent.OnGoogleSignInResult(
                    LoginResult(
                        0,
                        null
                    )
                )
            }
        }
    }
}