package mx.itesm.listbeacons

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.estimote.coresdk.common.requirements.SystemRequirementsChecker
import com.estimote.coresdk.observation.region.beacon.BeaconRegion
import com.estimote.coresdk.recognition.packets.Beacon
import com.estimote.coresdk.service.BeaconManager
import java.util.*

class MainActivity : AppCompatActivity() {

    private var adminBeacons: BeaconManager? = null
    private var region: BeaconRegion? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adminBeacons = BeaconManager(this)
        region = BeaconRegion("MyBeacons",
            UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"), null, null)

        adminBeacons?.setRangingListener{
            beaconRegion: BeaconRegion?, beacons: MutableList<Beacon>? ->
            println("Beacons detected: $beacons")
        }

    }

    override fun onResume() {
        super.onResume()
        SystemRequirementsChecker.checkWithDefaultDialogs(this)
        adminBeacons?.connect {
            adminBeacons?.startRanging(region)
        }
    }

    override fun onStop() {
        adminBeacons?.stopRanging(region)
        super.onStop()
    }

}
