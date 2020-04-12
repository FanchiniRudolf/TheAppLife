package mx.itesm.rjfr.fragmentos

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Fragmento inicial
        val fragHome = HomeFrag()
        supportFragmentManager.beginTransaction()
            .replace(R.id.contenedorFragmentos, fragHome)
            .commit()


        // esta referencia es el menu inferior
        val navView: BottomNavigationView = findViewById(R.id.nav_view)


        navView.setOnNavigationItemSelectedListener { menuItem ->
            // que haremos cuando el user seleccione un item
            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    Log.i("NAVIGATION", "el usuario ha oprimido home")
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.contenedorFragmentos, fragHome)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }

                R.id.navigation_dashboard -> {
                    Log.i("NAVIGATION", "el usuario ha oprimido dashboard")
                    val fragDashboard = DashboardFrag()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.contenedorFragmentos, fragDashboard)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                        .addToBackStack(null)
                        .commit()
                }

                R.id.navigation_notifications -> {
                    Log.i("NAVIGATION","el usuario ha oprimido notifications")
                    val fragNotifications = NotificationsFrag()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.contenedorFragmentos, fragNotifications)
                        .addToBackStack(null)
                        .commit()
                }

                R.id.navigation_other -> {
                    Log.i("NAVIGATION", "el usuario ha oprimido other")
                    val fragOtro = OtroFrag()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.contenedorFragmentos, fragOtro)
                        .addToBackStack(null)
                        .commit()
                }
                else ->
                    println("NO disponible")
            }

            true
        }
    }
}
