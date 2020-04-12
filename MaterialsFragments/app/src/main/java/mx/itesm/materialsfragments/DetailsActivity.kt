package mx.itesm.materialsfragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val index = intent.getIntExtra("Position",0)

        val fragment = fragMateriaDetalle as MaterialDetailsFrag;
        fragment.index = index
    }


}
