package mx.itesm.materialsfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_material_details.*

/**
 * A simple [Fragment] subclass.
 */
class MaterialDetailsFrag : Fragment() {

    var index = 0
        set (value){
            if (value>=0){
                field = value
            }
        }

    override fun onStart() {
        super.onStart()
        if (view != null){
            val material = Material.arrMaterias[index]
            tvTittle.text = material.nombre
            tvDetails.text = material.descripcion
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_material_details, container, false)
    }

}
