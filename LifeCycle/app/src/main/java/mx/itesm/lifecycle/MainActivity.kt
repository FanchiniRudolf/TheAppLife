
package mx.itesm.lifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println("On Create")
    }

    override fun onStart() {
        super.onStart()
        println("On Start")
    }

    override fun onResume() {
        super.onResume()
        println("On Resume")
    }

    override fun onPause() {
        super.onPause()
        println("On Pause")
    }

    override fun onRestart() {
        super.onRestart()
        println("On Restart")
    }

    override fun onStop() {
        println("On Stop")
        super.onStop()
    }

    override fun onDestroy() {
        println("On Destroy")
        super.onDestroy()
    }
}
