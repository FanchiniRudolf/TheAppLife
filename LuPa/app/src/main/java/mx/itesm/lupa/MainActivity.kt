package mx.itesm.lupa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val Lupa: LuPa = LuPa()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageButton.setOnClickListener { view ->
            //todo Launch new screen
            val intAboutInfo = Intent(this, AboutActivity::class.java)
            startActivity(intAboutInfo)
            finish() //remove current int from stack
        }

        Lupa.shuffleGame()
        setAllButtonsValue()
    }

    fun blockButtons() {
        button.isClickable = false
        button2.isClickable = false
        button3.isClickable = false
        button4.isClickable = false
        button5.isClickable = false
        button6.isClickable = false
        button7.isClickable = false

    }

    fun releaseButtons() {
        button.isClickable  = true
        button2.isClickable = true
        button3.isClickable = true
        button4.isClickable = true
        button5.isClickable = true
        button6.isClickable = true
        button7.isClickable = true
    }


    fun resetAllButtonsValue(v: View){
        Lupa.shuffleGame()
        setAllButtonsValue()
        releaseButtons()
    }

    fun setAllButtonsValue(){
        var btnArray = Lupa.gameValuesArr
        for (i in btnArray.indices){
            when(i){
                0 -> button.text = btnArray[i].toString()
                1 -> button2.text = btnArray[i].toString()
                2 -> button3.text = btnArray[i].toString()
                3 -> button4.text = btnArray[i].toString()
                4 -> button5.text = btnArray[i].toString()
                5 -> button6.text = btnArray[i].toString()
                6 -> button7.text = btnArray[i].toString()
            }
        }

    }

    //todo
    fun changeSingleButtonValue(v: View){
        when(v) {
            button  -> Lupa.changeContiguous(0)
            button2 -> Lupa.changeContiguous(1)
            button3 -> Lupa.changeContiguous(2)
            button4 -> Lupa.changeContiguous(3)
            button5 -> Lupa.changeContiguous(4)
            button6 -> Lupa.changeContiguous(5)
            button7 -> Lupa.changeContiguous(6)
        }
        setAllButtonsValue()
        checkForWin()
    }

    fun checkForWin() {
        if (Lupa.hasWon()){
            blockButtons()
            showMessage("¡Ganaste! \nTus movimientos fueron: ${Lupa.getMoves() }")
        }
    }



    private fun showMessage(msg: String) {
        val alert = AlertDialog.Builder(this)
        alert.setMessage(msg).setTitle("Aviso")
                .setPositiveButton("Aceptar", null)
                .setCancelable(false)
                .create()
        alert.show()
    }
}
