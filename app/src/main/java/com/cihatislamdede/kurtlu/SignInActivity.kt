package com.cihatislamdede.kurtlu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.parse.Parse
import com.parse.ParseException
import com.parse.ParseUser
import kotlinx.android.synthetic.main.activity_main.*

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val button = findViewById<Button>(R.id.girisEkranGo)

        val parseUser = ParseUser.getCurrentUser()
        if(parseUser !=null){
            val intent = Intent(applicationContext,
                LocationsActivity::class.java)
            startActivity(intent)
            finish()
        }


    }

    fun goGirisEkrani(view: View){
        val intent = Intent(this, SignInActivity2::class.java)
        startActivity(intent)

    }
    fun signUp(view:View){
        val user = ParseUser()
        user.username = usernameText.text.toString()
        user.setPassword(passwordText.text.toString())
        user.signUpInBackground { e:ParseException? ->
            if (e != null){
                Toast.makeText(applicationContext,e.localizedMessage,Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(applicationContext,"Kayit Başarılı",Toast.LENGTH_LONG).show()
                val intent = Intent(applicationContext,
                    LocationsActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }



}
