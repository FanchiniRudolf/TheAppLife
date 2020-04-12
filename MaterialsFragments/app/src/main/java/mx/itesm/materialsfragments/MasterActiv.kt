package mx.itesm.materialsfragments

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_details.*
import mx.itesm.Materiallsfragments.MaterialListFrag

class MasterActiv : AppCompatActivity(), MaterialListFrag.Companion.ListListener {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        }

    override fun itemClicked(index: Int) {
        //Change Screen
        var intDetail = Intent(this, DetailsActivity::class.java)
        intDetail.putExtra("Position", index)
        startActivity(intDetail)
    }
}


