package actividades

import adaptadores.AdapterCategoria
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.indumentaria.R
import enlaceConFirebase.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_fragmento_lista.*

class MainActivity : AppCompatActivity() {

    private var adapter: AdapterCategoria? = null
    var btAdministrador: Button? = null

    private val viewModel by lazy { ViewModelProviders.of(this).get(MainViewModel::class.java) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // ir al activity Registrarse...............................

        btAdministrador = findViewById(R.id.btAdministrador)
        btAdministrador?.setOnClickListener {
            val intent = Intent(this, ActividadRegistrarse::class.java)
            startActivity(intent)
        }

        adapter = AdapterCategoria(mutableListOf(), this)
        recycler_categoria.layoutManager = GridLayoutManager(this, 2)
        recycler_categoria.adapter = adapter

        loadCategoria()
    }


    private fun loadCategoria(){
        viewModel.fetchCategoria().observe(this, Observer {
            adapter?.mutableList = it
            adapter?.notifyDataSetChanged()
            //Log.e("TagCategoria", it.toString())

        })
    }



}
