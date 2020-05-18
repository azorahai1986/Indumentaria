package actividades

import adaptadores.AdapterCategoria
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.indumentaria.R
import enlaceConFirebase.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_fragmento_lista.*

class MainActivity : AppCompatActivity() {



    private var adapter: AdapterCategoria? = null

    private val viewModel by lazy { ViewModelProviders.of(this).get(MainViewModel::class.java) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         // otra forma de darle click a varios boton o cardView que cumplan la misma accion.
        // en este caso van al siguiente activity
    /*    val clickListener: View.OnClickListener = View.OnClickListener { view ->
            when (view.id) {
                R.id.cardViewPromo -> IrAlActivity()
                R.id.cardViewRemeras -> IrAlActivity()
                R.id.cardViewCalsas -> IrAlActivity()
                R.id.cardViewSweaters -> IrAlActivity()
            }
        }
        cardViewPromo.setOnClickListener(clickListener)
        cardViewRemeras.setOnClickListener(clickListener)
        cardViewCalsas.setOnClickListener(clickListener)
        cardViewSweaters.setOnClickListener(clickListener)*/

        adapter = AdapterCategoria(mutableListOf(), this)
        recycler_categoria.layoutManager = GridLayoutManager(this, 2)
        recycler_categoria.adapter = adapter

        loadCategoria()
    }

    private fun loadCategoria(){
        viewModel.fetchCategoria().observe(this, Observer {
            adapter?.mutableList = it
            adapter?.notifyDataSetChanged()
        })
    }

    private fun IrAlActivity() {
        val intent = Intent(this, ActividadLista::class.java)
        startActivity(intent)
    }

}
