package fragmentos

import actividades.ActividadLista
import adaptadores.AdaptadorSubCategoria
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.indumentaria.R
import enlaceConFirebase.MainViewModel
import modeloDeDatos.SubCategoria
import java.lang.ClassCastException
import java.util.ArrayList


class FragmentoSubCategorias : Fragment() {
    private val viewModel by lazy { ViewModelProviders.of(this).get(MainViewModel::class.java) }
    var listener: FragmentoEnActividad? = null

    var recyclerSubCat: RecyclerView? = null
    var adaptadorSubCat: AdaptadorSubCategoria? = null
    var layoutManager: RecyclerView.LayoutManager? = null

    /**
     * idCAtegoria
     */
    var idSubCategoria: String? = null
    /**
     * newInstance fragment
     */
    companion object{
        const val TAGSUB = "FragmentoTagSub"
        private const val ID_SUBCAT = "ID_SUBCAT"

        fun newInstanceSub(idSubCategoria: String): FragmentoSubCategorias {
            val bun = Bundle()
            bun.putString(ID_SUBCAT, idSubCategoria)
            val frag = FragmentoSubCategorias()
            frag.arguments = bun
            return frag
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        idSubCategoria = arguments?.getString(ID_SUBCAT)

        // Inflate the layout for this fragment
        val vista = inflater.inflate(R.layout.fragment_fragmento_sub_categorias, container, false)

        recyclerSubCat = vista.findViewById(R.id.recycler_subCategoria)
        recyclerSubCat?.setHasFixedSize(true)
        layoutManager = GridLayoutManager(activity, 2)
        recyclerSubCat?.layoutManager = layoutManager


        adaptadorSubCat = AdaptadorSubCategoria(mutableListOf(), FragmentActivity())
        recyclerSubCat?.adapter = adaptadorSubCat

        observeDataSub()

        return vista
    }
    private fun observeDataSub(){

        viewModel.fetchSubCategoria(getString(id)).observe(this.viewLifecycleOwner, Observer {
            adaptadorSubCat!!.mutableListSub = it
            adaptadorSubCat!!.notifyDataSetChanged()

        })
    }
    interface FragmentoEnActividad{



    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // el linstener será el que envie o me deje acceder a la actividad. su contexto es la interface
        // lo hare mediante un try catch. en el caso que haya un error el classCastExeption me mostrará el error
        try {
            listener = context as FragmentoEnActividad

        }catch (e: ClassCastException){
            throw ClassCastException(context.toString() + "debes implementar interfaz") // en el main debo llamar a la interface
        }
    }


}
