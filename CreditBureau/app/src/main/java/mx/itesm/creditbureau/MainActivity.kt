package mx.itesm.creditbureau

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private val CALC_CREDIT_CODE = 600

    fun calculateCredit(v: View){

        val intCalcCredit = Intent(this, CalculateCreditActivity::class.java)
        val cardType = spinnerCardType.selectedItemPosition
        intCalcCredit.putExtra("cardType", cardType)
        //Return values
        startActivityForResult(intCalcCredit, CALC_CREDIT_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CALC_CREDIT_CODE){
            if (resultCode == Activity.RESULT_OK){
                val creditLimit = data?.getIntExtra("creditLimit", -1) ?: "Error"
                etCreditLimit.setText("$creditLimit")
            }
        }
    }

    fun loadBrowser(v: View){
        val url= Uri.parse("http://i.imgur.com/H5TnJxZ.gifv")
        val intBrowser = Intent(Intent.ACTION_VIEW, url)

        startActivity(intBrowser)
    }
}
