package mx.itesm.rockpaperscissor

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {


    private val rps: RockPaperScissor = RockPaperScissor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        infoButton.setOnClickListener { view ->
            //todo Launch new screen
            val intAboutInfo = Intent(this, AboutActivity::class.java)
            startActivity(intAboutInfo)
            finish() //remove current int from stack
        }
    }


    fun play(v: View){
        var userPlay = Play.ROCK
        if(v==paperBtn){
            userPlay = Play.PAPER
        }else if (v==scissorBtn) userPlay = Play.SCISSOR

        val computerPlay = rps.playRandom()
        val result = rps.play(userPlay, computerPlay)

        showToastMessage("Usuario $userPlay, Compu $computerPlay", result)

        updateScores()

        checkForWinner()


    }

    private fun checkForWinner() {
        val status = rps.winner()
        if( status == PlayResult.PLAYER_WIN){
            showMessage("Felicidades ganaste")
        }else if(status == PlayResult.COMPUTER_WIN){
            showMessage("Suerte para la proxima")
        }
    }

    private fun showMessage(msg: String) {
        val alert = AlertDialog.Builder(this)
        alert.setMessage(msg).setPositiveButton("Reiniciar juego", DialogInterface.OnClickListener{
            dialog, which ->
                rps.reset()
                updateScores()
        })
            .setNegativeButton("Cancelar", null )
            .setCancelable(false)
            .create()
        alert.show()
    }

    private fun showSnackMessage(msg: String) {
        Snackbar.make(rockBtn, msg, Snackbar.LENGTH_LONG)
    }

    private fun updateScores() {
        val playerPoints = rps.playerPoints
        val computerPoints = rps.computerPoints

        etPlayer.setText(playerPoints.toString())
        etComputer.setText(computerPoints.toString())
    }

    private fun showToastMessage(msg: String, result: PlayResult) {
        Toast.makeText(this, msg + "\n" + result, Toast.LENGTH_SHORT).show()
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
}
