package lu.andlim.tugas4challangelukman

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.alert_inputdata.*
import kotlinx.android.synthetic.main.alert_inputdata.view.*
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class DashboardFragment : Fragment() {

    var dbschool : SchoolDatabase? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dbschool = SchoolDatabase.getInstance(requireContext())

        // input data
        fab_add.setOnClickListener {
           val add = AlertDialog.Builder(requireContext())
               .create()
            add.setTitle("Input Data")
            val custum = LayoutInflater.from(requireContext())
                .inflate(R.layout.alert_inputdata, null, false)
            add.setView(custum)
            add.create()
            custum.btn_simpan.setOnClickListener {
                GlobalScope.async {
                    val sekolah = edt_inputsekolah.text.toString()
                    val alamat = edt_inputalamat.text.toString()
                    val tahun = edt_inputtahun.text.toString()

                    val simpan = dbschool?.schoolDao()?.insertSchool(School(null, sekolah, alamat, tahun))
                    activity?.runOnUiThread {
                        if (simpan != 0.toLong()){
                            Toast.makeText(requireContext(),"SUCCES", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(requireContext(),"GAGAL", Toast.LENGTH_LONG).show()
                        }
                    }
                }
                add.dismiss()
            }
            add.show()
        }

        getDataSchool()
        
    }
    
    fun getDataSchool(){
        rv_sekolah.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        GlobalScope.launch { 
            val listdata = dbschool?.schoolDao()?.getAllSchool()
            activity?.runOnUiThread { 
                listdata.let {
                    val adapt = AdapterSchool(it!!)
                    rv_sekolah.adapter = adapt
                }
            }
        }
    }

}