package lu.andlim.tugas4challangelukman

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recy_data.view.*

class AdapterSchool(val listDataSchool : List<School>) : RecyclerView.Adapter<AdapterSchool.ViewHolder>() {

    private var adb : SchoolDatabase? = null
    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterSchool.ViewHolder {
        val viewitem = LayoutInflater.from(parent.context)
            .inflate(R.layout.recy_data, parent, false)
        return ViewHolder(viewitem)
    }

    override fun onBindViewHolder(holder: AdapterSchool.ViewHolder, position: Int) {
        holder.itemView.text_id.text = listDataSchool[position].id.toString()
        holder.itemView.text_sekolah.text = listDataSchool[position].sekolah
        holder.itemView.text_alamat.text = listDataSchool[position].alamat
        holder.itemView.text_tahun.text = listDataSchool[position].tahun
    }

    override fun getItemCount(): Int {
        return listDataSchool.size
    }
}