package mx.itesm.creditbureau

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_calculate_credit.*

class CalculateCreditActivity : AppCompatActivity() {

    private var cardType = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculate_credit)

        cardType = intent.getIntExtra("cardType", 0)
    }


    fun goBack(v: View){
        var salary = etSalary.text.toString().toInt()
        val creditLimit = when(cardType){
            1 -> (salary*2.5).toInt()
            2 -> (salary*5.2).toInt()
            else -> 0
        }
        val intBack = Intent()
        intBack.putExtra("creditLimit", creditLimit)
        setResult(Activity.RESULT_OK, intBack)
        finish()
    }

}
