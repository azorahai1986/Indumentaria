package actividades

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import adaptadores.AdaptadorViewPager
import android.util.Log
import fragmentos.FragmentoLista
import com.example.indumentaria.R
import fragmentos.FragmentoSubCategorias
import kotlinx.android.synthetic.main.activity_actividad_lista.*

class ActividadLista : AppCompatActivity(), FragmentoSubCategorias.FragmentoEnActividad, FragmentoLista.FragmentoEnActivity {

    private var idCategoria: String? = null
    //private var idSubCategoria: String? = null
    companion object{
        const val ID_CATEGORIA = "ID_Categoria"
        //const val ID_SUBCATEGORIA = "ID_SubCategoria"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividad_lista)

        /* Enviar info al siguiente fragment*/
        /**
         * recuperar parametro del otro activity
         */
        idCategoria = intent.getStringExtra(ID_CATEGORIA)
        Log.e("ActividadLista", idCategoria.toString())

        //idSubCategoria = intent.getStringExtra(ID_SUBCATEGORIA)

        configurarViewPager()
       // tabs.setupWithViewPager(viewPager)

    }

      // esta funcion es para crear las pestañas del viewPager
    fun configurarViewPager(){
        val adaptador = AdaptadorViewPager(supportFragmentManager)
          // en el primer fragmento tome la instancia del idCategoria. o sea. de la lista de categorias.
        adaptador.agregarFragmento(FragmentoSubCategorias.newInstanceSub(idCategoria!!), "")
          //adaptador.agregarFragmento(FragmentoLista, "FragmentoPromo")
        viewPager.adapter = adaptador
    }



}
