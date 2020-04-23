package mx.itesm.rjfr.firebase_rudolf

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.ListFragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class StudentsFrag : ListFragment() {

   private lateinit var arrStudents: MutableList<String>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arrStudents = mutableListOf()
    }

    override fun onStart() {
        super.onStart()
        readData()
    }

    private fun readData(){
        val dataBase = FirebaseDatabase.getInstance()
        val reference = dataBase.getReference("/Students")
        reference.addValueEventListener(object: ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                arrStudents.clear()
                for (registry in snapshot.children){
                    val student = registry.getValue(Student::class.java)
                    arrStudents.add("${student?.name} -- ${student?.student_id}")
                }
                val adapter = ArrayAdapter<String>(context!!, android.R.layout.simple_list_item_1, arrStudents)
                listAdapter = adapter
            }

        })


    }


}
