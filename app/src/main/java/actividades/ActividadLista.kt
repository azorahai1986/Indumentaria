package actividades

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

class ActividadLista : AppCompatActivity(), FragmentoLista.FragmentoEnActivity {

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
            .replace(R.id.contenedor_lista,
                FragmentoSubCategorias.newInstanceSub(idCategoria!!),
                FragmentoSubCategorias.TAGSUB).commit()



    }



}
