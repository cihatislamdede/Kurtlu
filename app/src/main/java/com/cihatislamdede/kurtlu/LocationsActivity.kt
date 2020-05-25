package com.cihatislamdede.kurtlu

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemLongClickListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.parse.ParseObject
import com.parse.ParseQuery
import com.parse.ParseUser
import kotlinx.android.synthetic.main.activity_locations.*

class LocationsActivity : AppCompatActivity() {

    var namesArray = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_locations)

        getParseData()

        listView.setOnItemClickListener { adapterView, view, i, l ->
            val intent = Intent(applicationContext,DetailActivity::class.java)
            intent.putExtra("name",namesArray[i])
            startActivity(intent)
        }






    }

    fun goAddPlace(view: View){
        val intent = Intent(applicationContext,PlaceNameActivity::class.java)
        startActivity(intent)
    }

    fun exitButton(view:View){
        val intent = Intent(applicationContext,SignInActivity::class.java)
        startActivity(intent)
        var parseUser = ParseUser.logOut()
        finish()
    }

    fun getParseData(){

        val arrayAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,namesArray)
        listView.adapter = arrayAdapter

        val query = ParseQuery.getQuery<ParseObject>("Locations")
        query.findInBackground { objects, e ->

            if (e!= null) {
                Toast.makeText(applicationContext,e.localizedMessage,Toast.LENGTH_LONG).show()
            } else {

                if (objects.size > 0) {
                    namesArray.clear()

                    for (parseObject in objects) {
                        val name = parseObject.get("name") as String
                        namesArray.add(name)
                    }
                    arrayAdapter.notifyDataSetChanged()

                }


            }


        }


    }

    fun refresh(view:View){
        finish();
        startActivity(intent);
    }
}
