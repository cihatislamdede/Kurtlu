package com.cihatislamdede.kurtlu

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.parse.ParseFile
import com.parse.ParseObject
import com.parse.ParseQuery
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), OnMapReadyCallback {

    var chosenPlace = ""
    private lateinit var mMap: GoogleMap



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapView) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val intent = intent
        chosenPlace = intent.getStringExtra("name")


    }

    override fun onMapReady(p0: GoogleMap) {
        mMap = p0

        val query = ParseQuery<ParseObject>("Locations")
        query.whereEqualTo("name",chosenPlace)
        query.findInBackground { objects, e ->
            if(e!=null){
                Toast.makeText(applicationContext,e.localizedMessage,Toast.LENGTH_SHORT).show()
            }else{
                if(objects.size>0){
                    for (parseObject in objects){
                        val image = parseObject.get("image") as ParseFile
                        image.getDataInBackground { data, e ->
                            if(e!=null){
                                Toast.makeText(applicationContext,e.localizedMessage,Toast.LENGTH_SHORT).show()
                            }else{
                                val bitmap = BitmapFactory.decodeByteArray(data,0,data.size)
                                detailImageview.setImageBitmap(bitmap)
                                val name = parseObject.get("name") as String
                                val latitude = parseObject.get("latitude") as String
                                val longitude = parseObject.get("longitude") as String
                                val type = parseObject.get("type") as String
                                val rating = parseObject.get("rating") as String
                                val ekleyenUser = parseObject.get("username") as String

                                ekleyenUserText.text = "Kurtlu Gezgin: ${ekleyenUser}"
                                nameTextView.text = name
                                typeTextView.text = type
                                ratingTextView.text = "Puanı: ${rating}"

                                val latitudeDouble = latitude.toDouble()
                                val longitudeDouble = longitude.toDouble()

                                val choosenLocation = LatLng(latitudeDouble,longitudeDouble)
                                mMap.addMarker(MarkerOptions().position(choosenLocation).title(name))
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(choosenLocation,18f))


                            }
                        }
                    }
                }
            }
        }

    }

}
