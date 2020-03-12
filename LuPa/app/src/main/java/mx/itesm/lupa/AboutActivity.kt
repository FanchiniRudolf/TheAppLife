package mx.itesm.lupa

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        button8.setOnClickListener { view ->
            //todo Launch new screen
            val intMain = Intent(this, MainActivity::class.java)
            startActivity(intMain)
            finish() //remove current int from stack
        }
    }
}