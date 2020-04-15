package mx.itesm.rjfr.covid19

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.country_line.view.*

class CountryAdapter(private val context: Context, var countryArr: Array<Country>) :
    RecyclerView.Adapter<CountryAdapter.CountryLine>(){

    var listener: ListenerRecycler? = null

    inner class CountryLine(var lineView: View): RecyclerView.ViewHolder(lineView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryLine {
        val view = LayoutInflater.from(context).inflate(
            R.layout.country_line, parent, false
        )
        return CountryLine(view)
    }

    override fun getItemCount(): Int {
        return countryArr.size //Number of lines on component
    }

    override fun onBindViewHolder(holder: CountryLine, position: Int) {
        val country = countryArr[position];
        holder.lineView.tvCountry.text = country.name
        holder.lineView.tvCases.text = country.cases.toString()
        holder.lineView.imgFlag.setImageResource(R.drawable.flag)

        //Event
        holder.lineView.setOnClickListener{
            listener?.itemClicked(position)
        }
    }
}