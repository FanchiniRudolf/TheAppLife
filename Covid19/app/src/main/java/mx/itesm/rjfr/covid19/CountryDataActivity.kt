package mx.itesm.rjfr.covid19

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.BitmapRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.activity_country_data.*
import org.json.JSONObject

class CountryDataActivity : AppCompatActivity() {


    var entries = ArrayList<Entry>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_data)

        val name = intent.getStringExtra("COUNTRY")
        tvCountryName.text = name

        downloadData(name)
        dowmloadChart(name)

    }

    private fun downloadData(name: String) {
        val address = "https://corona.lmao.ninja/countries/$name"

        AndroidNetworking.get(address)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {

                    val cases = response?.get("cases")
                    tvCasesCountry.text = "$cases"
                    val recuperated = response?.get("recovered")
                    tvRecuparatedCountry.text = "$recuperated"
                    val deceased = response?.get("deaths")
                    tvDeceasedCountry.text = "$deceased"

                    val infoDict = response?.get("countryInfo") as JSONObject
                    val flagAddress = infoDict?.get("flag") as String?
                    downloadFlag(flagAddress)

                }

                override fun onError(anError: ANError?) {
                    Toast.makeText(this@CountryDataActivity, "Error Country: $anError", Toast.LENGTH_LONG)
                        .show()
                }

            })
    }

    private fun createChart() {
        val data = LineDataSet(entries, "Personas")
        data.setDrawValues(true)
        data.lineWidth = 3f
        lineChart.data = LineData(data)
        lineChart.description.text = "COVID-19"
        lineChart.animateX(1800, Easing.EaseInOutSine)
    }

    private fun dowmloadChart(countryName: String?) {
        val address = "https://corona.lmao.ninja/v2/historical/$countryName"


        AndroidNetworking.get(address)
            .build()
            .getAsJSONObject(object: JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                    val timeline = response?.get("timeline") as JSONObject?
                    val cases = timeline?.get("cases") as JSONObject
                    var index = 0f
                    for (date in cases.keys()) {
                        val valor = cases.get(date) as Int
                        if (valor>0) {
                            val entry = Entry(index, valor+0f)
                            entries.add(entry)
                            index += 1
                        }
                    }
                    createChart()

                }
                override fun onError(anError: ANError?) {
                    Toast.makeText(this@CountryDataActivity, "Error Graph: $anError", Toast.LENGTH_LONG)
                        .show()
                }
            })

    }

    private fun downloadFlag(address: String?) {
        AndroidNetworking.get(address)
            .build()
            .getAsBitmap(object : BitmapRequestListener {
                override fun onResponse(response: Bitmap?) {
                    imgCountryFlag.setImageBitmap(response)
                }

                override fun onError(anError: ANError?) {
                    Toast.makeText(this@CountryDataActivity, "Error Flag: $anError", Toast.LENGTH_LONG)
                        .show()
                }

            })
    }
}
