package mx.itesm.rjfr.covid19

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity(), ListenerRecycler {

    var countryAdapter: CountryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configureRecycler()

        AndroidNetworking.initialize(applicationContext)

        downloadInfo()
    }

    private fun downloadInfo() {
        val address = "https://corona.lmao.ninja/countries?sort=country"

        AndroidNetworking.get(address)
            .build()
            .getAsJSONArray(object: JSONArrayRequestListener{
                override fun onResponse(response: JSONArray?) {
                    if(response != null){
                        var arrCountry = mutableListOf<Country>()
                        for ( i in 0 until response.length()){
                            val countryDict = response.get(i) as JSONObject //dicionario
                            val name = countryDict.getString("country")
                            val cases = countryDict.getInt("cases")
                            arrCountry.add(0, Country(name, cases))

                        }
                        countryAdapter?.countryArr = arrCountry.toTypedArray()
                        countryAdapter?.notifyDataSetChanged()
                    }
                }

                override fun onError(anError: ANError?) {
                    Toast.makeText(this@MainActivity, "Error: $anError", Toast.LENGTH_LONG).show()
                }

            })
    }

    private fun configureRecycler(){
        val layout = LinearLayoutManager(this)
        layout.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layout

        countryAdapter = CountryAdapter(this, Country.countryArr)
        countryAdapter?.listener = this
        recyclerView.adapter = countryAdapter

        val lineDivisor = DividerItemDecoration(this, layout.orientation)
        recyclerView.addItemDecoration(lineDivisor)
    }

    override fun itemClicked(position: Int) {
        //Launch second screen
        val intentCountryDataActivity = Intent(this, CountryDataActivity::class.java)
        val name = countryAdapter?.countryArr?.get(position)?.name
        intentCountryDataActivity.putExtra("COUNTRY", name)
        startActivity(intentCountryDataActivity)
    }
}
