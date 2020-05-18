package actividades

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import adaptadores.AdaptadorViewPager
import fragmentos.FragmentoLista
import fragmentos.FragmentoPromo
import com.example.indumentaria.R
import kotlinx.android.synthetic.main.activity_actividad_lista.*
import modeloDeDatos.Categoria
import modeloDeDatos.ModeloDeIndumentaria
import java.util.ArrayList

class ActividadLista : AppCompatActivity(), FragmentoLista.FragmentoEnActivity {

    private var idCategoria: String? = null
    companion object{
        const val ID_CATEGORIA = "ID_Categoria"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividad_lista)
        /**
         * recuperar parametro del otro activity
         */
        idCategoria = intent.getStringExtra(ID_CATEGORIA)

        configurarViewPager()
        tabs.setupWithViewPager(viewPager)

    }
      // esta funcion es para crear las pestañas del viewPager
    fun configurarViewPager(){
        val adaptador = AdaptadorViewPager(supportFragmentManager)
        adaptador.agregarFragmento(FragmentoLista.newInstance(idCategoria!!), "FragmentoLista")
        adaptador.agregarFragmento(FragmentoPromo(), "FragmentoPromo")
        viewPager.adapter = adaptador
    }


}
