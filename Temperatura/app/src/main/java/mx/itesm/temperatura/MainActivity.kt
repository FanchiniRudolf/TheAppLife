package mx.itesm.temperatura

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun calcCelsius(v: View){
            //F->C
            val tempF = etTemp.text.toString().toDouble();
            val tempC = (5.0/9.0)*(tempF-32);
            etRes.text = "Resultado ${tempC.toString()} C°";
    }

    fun calcFahren(v: View){
        //C->F
        val tempC = etTemp.text.toString().toDouble();
        val convertidor = Convertidor(tempC)
        val tempF = convertidor.fahrenheit
        etRes.text = "Resultado ${tempF.toString()} C°";
    }
}

class Convertidor(val temp: Double){

    val fahrenheit: Double
        get() = (9.0/5)*temp+32
}