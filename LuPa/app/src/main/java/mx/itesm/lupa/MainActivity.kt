package mx.itesm.lupa

import android.app.Activity
import android.content.DialogInterface
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
            button  -> Lupa.changeContiguos(0)
            button2 -> Lupa.changeContiguos(1)
            button3 -> Lupa.changeContiguos(2)
            button4 -> Lupa.changeContiguos(3)
            button5 -> Lupa.changeContiguos(4)
            button6 -> Lupa.changeContiguos(5)
            button7 -> Lupa.changeContiguos(6)
        }
        setAllButtonsValue()
        checkForWin()
    }

    fun checkForWin() {
        if (Lupa.hasWon()){
            blockButtons()
            showMessage("¡Ganaste!")
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
