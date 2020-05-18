package fragmentos

import actividades.ActividadLista
import adaptadores.AdaptadorRecycler
import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.indumentaria.R
import enlaceConFirebase.MainViewModel
import modeloDeDatos.ModeloDeIndumentaria
import java.lang.ClassCastException
import java.util.ArrayList


class FragmentoLista : Fragment() {
    var listener:FragmentoEnActivity? = null  // del fragmento
    var recyclerView: RecyclerView? = null
    private var adaptador: AdaptadorRecycler? = null
    var layoutManager: RecyclerView.LayoutManager? = null

    /**
     * idCAtegoria
     */
    var idCategoria: String? = null
    /**
     * newInstance fragment
     */
    companion object{
        const val TAG = "FragmentoTag"
        private const val ID_CAT = "ID_CAT"

        fun newInstance(idCategoria: String): FragmentoLista{
            val bn = Bundle()
            bn.putString(ID_CAT, idCategoria)
            val fr = FragmentoLista()
            fr.arguments = bn
            return  fr
        }
    }

    // aca inicializo el ViewModel
    private val viewModel by lazy { ViewModelProviders.of(this).get(MainViewModel::class.java) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        idCategoria = arguments?.getString(ID_CAT)


        val vista = inflater.inflate(R.layout.fragment_fragmento_lista, container, false)
        recyclerView = vista.findViewById(R.id.recyclerView)
        recyclerView?.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(activity)
        recyclerView?.layoutManager = layoutManager
        val indumentaria = ArrayList<ModeloDeIndumentaria>()

        adaptador = AdaptadorRecycler(indumentaria, context!!)
        recyclerView?.adapter = adaptador

        observeData()
        return vista

    }

    private fun observeData(){

        viewModel.fetchUserData().observe(this.viewLifecycleOwner, Observer {
            adaptador!!.setListData(ArrayList(it))
            adaptador!!.notifyDataSetChanged()

        })
    }


    private fun loadSubcategoria(){
        viewModel.fetchSubCategoria(idCategoria!!).observe(this.viewLifecycleOwner, Observer {

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
