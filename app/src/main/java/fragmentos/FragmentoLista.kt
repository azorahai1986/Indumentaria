package fragmentos

import actividades.ActividadLista
import adaptadores.AdaptadorRecycler
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.indumentaria.R
import enlaceConFirebase.MainViewModel
import modeloDeDatos.ModeloDeIndumentaria
import modeloDeDatos.SubCategoria
import java.lang.ClassCastException
import kotlin.collections.ArrayList


class FragmentoLista : Fragment() {
    var listener:FragmentoEnActivity? = null  // del fragmento
    var recyclerView: RecyclerView? = null
    private var adaptadorLista: AdaptadorRecycler? = null
    var layoutManager: RecyclerView.LayoutManager? = null

    /**
     * idCAtegoria
     */
    var idSubCategoria: String? = null
    /**
     * newInstance fragment
     */
    companion object{
        const val TAG = "FragmentoLista"

        private const val ID_LIST = "ID_LIST"

        fun newInstanceList(idSubCategoria: String): FragmentoLista{
            val bn = Bundle()
            bn.putString(ID_LIST, idSubCategoria)
            val fr = FragmentoLista()
            fr.arguments = bn
            Log.e("ID_LIST", bn.toString())

            return  fr
        }
    }

    // aca inicializo el ViewModel
    private val viewModel by lazy { ViewModelProviders.of(this).get(MainViewModel::class.java) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        idSubCategoria = arguments?.getString(ID_LIST)


        val vista = inflater.inflate(R.layout.fragment_fragmento_lista, container, false)
        recyclerView = vista.findViewById(R.id.recyclerView)
        adaptadorLista = AdaptadorRecycler(arrayListOf(), context as FragmentActivity)
        recyclerView?.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(activity)
        recyclerView?.layoutManager = layoutManager

        recyclerView?.adapter = adaptadorLista

        observeData()
        return vista

    }

    private fun observeData(){

        viewModel.fetchUserData(idSubCategoria!!).observe(this.viewLifecycleOwner, Observer {
            adaptadorLista!!.mutableListModel = it as ArrayList<ModeloDeIndumentaria>
            adaptadorLista!!.notifyDataSetChanged()

        })
    }



    interface FragmentoEnActivity{



    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // el linstener será el que envie o me deje acceder a la actividad. su contexto es la interface
        // lo hare mediante un try catch. en el caso que haya un error el classCastExeption me mostrará el error
        try {
            listener = context as FragmentoEnActivity

        }catch (e: ClassCastException){
            throw ClassCastException(context.toString() + "debes implementar interfaz") // en el main debo llamar a la interface
        }
    }

}
