package mx.itesm.rjfr.gpspractice

import android.Manifest
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.json.JSONObject
import java.text.FieldPosition

class MainActivity : AppCompatActivity(), LocationListener {

    private val GPS_PERMIT: Int = 200
    private lateinit var gps: LocationManager
    private lateinit var position: Location

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        /*fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }*/
        AndroidNetworking.initialize(this)

    }

    fun showMap (v: View){
        val latitud = position.latitude
        val longitude = position.longitude
        val url = "https://www.google.com/maps/search/?api=1&query=$latitud,$longitude"
        val intMap = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intMap)
    }

    fun downloadAddress(v: View) {
        val latitud = position.latitude
        val longitude = position.longitude
        val address = "https://geocode.xyz/$latitud,$longitude?geoit=json"
        AndroidNetworking.get(address)
            .build()
            .getAsJSONObject(object: JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                    val  city = response?.getString("city")
                    val cp = response?.getString("postal")
                    val country = response?.getString("country")
                    val street = response?.getString("staddress")
                    val street_number = response?.getString("stnumber")
                    tvAddress.setText("$street #$street_number, $city, $cp, $country")

                }

                override fun onError(anError: ANError?) {
                    println("******************************************************")
                    println("error")
                    println("******************************************************")
                }

            })
    }

    override fun onStart() {
        super.onStart()
        configureGPS()
    }

    override fun onResume() {
        super.onResume()
        //Check for permit
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
            PackageManager.PERMISSION_GRANTED){
            //have permit allowed
            gps.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0f, this)
            onLocationChanged(gps.getLastKnownLocation(LocationManager.GPS_PROVIDER))
        } else{
            //ask for permit
            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), GPS_PERMIT)
        }
    }

    override fun onPause() {
        super.onPause()
        gps.removeUpdates(this)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == GPS_PERMIT && grantResults.isNotEmpty()) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Granted permit
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {
                    //have permit allowed
                    gps.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0f, this)
                    onLocationChanged(gps.getLastKnownLocation(LocationManager.GPS_PROVIDER))
                }
            } else {
                    //Show Message on how to do it
            }
        }
    }

    private fun configureGPS() {
        //create sensor gps admin
        gps = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if(!gps.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            //open android settings
            turnOnGPS()
        }
    }

    private fun turnOnGPS() {
        //user must turn it on app cannot do it
        val dialoge = AlertDialog.Builder(this)
        dialoge.setMessage("GPS is turned off, would you like to turn it on")
                .setCancelable(false)
                .setPositiveButton("Yes", object:DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                    }
                })
                .setNegativeButton("No", object:DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        dialog?.dismiss()
                    }
                })
        val alert = dialoge.create()
        alert.show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onLocationChanged(location: Location?) {
        if(location != null){
            tvLatitude.setText("${location.latitude}")
            tvLongitude.setText("${location.longitude}")
            position = location
        }
    }


    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
    }

    override fun onProviderEnabled(provider: String?) {
    }

    override fun onProviderDisabled(provider: String?) {
    }
}
