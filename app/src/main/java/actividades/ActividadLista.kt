package actividades

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import adaptadores.AdaptadorViewPager
import fragmentos.FragmentoLista
import fragmentos.FragmentoPromo
import com.example.indumentaria.R
import kotlinx.android.synthetic.main.activity_actividad_lista.*
import modeloDeDatos.ModeloDeIndumentaria
import java.util.ArrayList

class ActividadLista : AppCompatActivity(), FragmentoLista.FragmentoEnActivity {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividad_lista)


        configurarViewPager()
        tabs.setupWithViewPager(viewPager)

    }
      // esta funcion es para crear las pestañas del viewPager
    fun configurarViewPager(){
        val adaptador = AdaptadorViewPager(supportFragmentManager)
        adaptador.agregarFragmento(FragmentoLista(), "FragmentoLista")
        adaptador.agregarFragmento(FragmentoPromo(), "FragmentoPromo")
        viewPager.adapter = adaptador
    }

    override fun ObtenerModeloDatos(modelo: ArrayList<String>) {
        super.ObtenerModeloDatos(modelo)
        var arreglo = ArrayList<ModeloDeIndumentaria>()

    }
}
