package com.cihatislamdede.kurtlu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.cihatislamdede.kurtlu.R
import com.parse.LogInCallback
import com.parse.ParseUser
import kotlinx.android.synthetic.main.activity_sign_in2.*

class SignInActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in2)
    }


    fun signIn(view: View){
        ParseUser.logInInBackground(usernameText2.text.toString(),passwordText2.text.toString(),
            LogInCallback { user, e ->
                if (e !=null){
                    Toast.makeText(applicationContext,e.localizedMessage, Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(applicationContext,"Hoşgeldin," + user.username.toString() ,Toast.LENGTH_LONG).show()
                    val intent = Intent(applicationContext,
                        LocationsActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            })
    }
}
