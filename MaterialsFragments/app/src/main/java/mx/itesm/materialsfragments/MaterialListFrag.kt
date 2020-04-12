package mx.itesm.Materiallsfragments

import android.R
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.ListFragment
import mx.itesm.materialsfragments.MasterActiv
import mx.itesm.materialsfragments.Material

/**
 * A simple [Fragment] subclass.
 */
class MaterialListFrag : ListFragment() {

    //Interface
    companion object {
        interface ListListener {
            fun itemClicked(index: Int)
        }
    }

    var listener: ListListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ListListener) {
            listener = context as ListListener
        }else{
            println("error no es lista")
        }
    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        listener?.itemClicked(position)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val vista = super.onCreateView(inflater, container, savedInstanceState)
        var Materials = Array<String>(Material.arrMaterias.size, { i -> ""} )
        var materials = Array<String>(Material.arrMaterias.size)
        { i -> Material.arrMaterias[i].nombre }
        val adaptador =
            ArrayAdapter<String>(inflater.context, R.layout.simple_list_item_1, materials)
        listAdapter = adaptador
        return vista
    }

}
