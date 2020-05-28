package fragmentos

import adaptadores.AdaptadorSubCategoria
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.indumentaria.R
import enlaceConFirebase.MainViewModel
import kotlinx.android.synthetic.main.template_subcategoria.*
import modeloDeDatos.SubCategoria
import java.lang.ClassCastException


class FragmentoSubCategorias : Fragment() {
    private val viewModel by lazy { ViewModelProviders.of(this).get(MainViewModel::class.java) }
    var listener: FragmentoEnActividad? = null

    var recyclerSubCat: RecyclerView? = null
    var adaptadorSubCat: AdaptadorSubCategoria? = null
    var layoutManager: RecyclerView.LayoutManager? = null

    /**
     * idCAtegoria
     */
    var idCategoria: String? = null
    /**
     * newInstance fragment
     */
    companion object{
        const val TAGSUB = "FragmentoTagSub"
        private const val ID_SUBCAT = "ID_SUBCAT"

        fun newInstanceSub(idCategoria: String): FragmentoSubCategorias {
            val bun = Bundle()
            bun.putString(ID_SUBCAT, idCategoria)
            val frag = FragmentoSubCategorias()
            frag.arguments = bun
            Log.e("ID_SUBCAT", bun.toString())
            return frag


        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        idCategoria = arguments?.getString(ID_SUBCAT)
        Log.e("ID_SUBCAT_EN_ONCREATE", idCategoria.toString())


        // Inflate the layout for this fragment
        val vista = inflater.inflate(R.layout.fragment_fragmento_sub_categorias, container, false)

        recyclerSubCat = vista.findViewById(R.id.recycler_subCategoria)
        adaptadorSubCat = AdaptadorSubCategoria(mutableListOf(), context as FragmentActivity)

        recyclerSubCat?.setHasFixedSize(true)
        layoutManager = GridLayoutManager(activity, 2)
        recyclerSubCat?.layoutManager = layoutManager
        recyclerSubCat?.adapter = adaptadorSubCat

        observeDataSub()

        Log.e("FRAG_SUBCAT", vista.toString())

        return vista

    }
    private fun observeDataSub(){

        viewModel.fetchSubCategoria(idCategoria).observe(this.viewLifecycleOwner, Observer {
            Log.e("Frag_SUB_CAT2", idCategoria.toString())
            adaptadorSubCat!!.mutableListSub = it
            Log.e("FRAG_SUB_3", it.toString())

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
