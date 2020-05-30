package actividades

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import adaptadores.AdaptadorViewPager
import android.content.Context
import android.util.Log
import fragmentos.FragmentoLista
import fragmentos.FragmentoPromo
import com.example.indumentaria.R
import fragmentos.FragmentoSubCategorias
import kotlinx.android.synthetic.main.activity_actividad_lista.*
import kotlinx.android.synthetic.main.template_subcategoria.*
import modeloDeDatos.Categoria
import modeloDeDatos.ModeloDeIndumentaria
import java.lang.ClassCastException
import java.util.ArrayList

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

        supportFragmentManager.beginTransaction()
            .replace(
                R.id.contenedor_lista,
                FragmentoSubCategorias.newInstanceSub(idCategoria!!),
                FragmentoSubCategorias.TAGSUB
            )
            .commit()

        //idSubCategoria = intent.getStringExtra(ID_SUBCATEGORIA)

     //   configurarViewPager()
        //tabs.setupWithViewPager(viewPager)

    }

      // esta funcion es para crear las pestañas del viewPager
    fun configurarViewPager(){
    /*    val adaptador = AdaptadorViewPager(supportFragmentManager)
          // en el primer fragmento tome la instancia del idCategoria. o sea. de la lista de categorias.
        adaptador.agregarFragmento(FragmentoSubCategorias.newInstanceSub(idCategoria!!), "FragmentoLista")
        adaptador.agregarFragmento(FragmentoPromo(), "FragmentoPromo")
        viewPager.adapter = adaptador

     */
    }



}
