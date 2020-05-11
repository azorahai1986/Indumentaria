package adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.indumentaria.R
import kotlinx.android.synthetic.main.template_lista_fragmento.view.*
import modeloDeDatos.ModeloDeIndumentaria

class AdaptadorRecycler(var datosFirebase: ArrayList<ModeloDeIndumentaria>, private val context: Context): RecyclerView.Adapter<AdaptadorRecycler.MainViewHolder>() {

    var arrayProductos = ArrayList<String>(datosFirebase.size)

    fun setListData(items: ArrayList<ModeloDeIndumentaria>){
         datosFirebase = items
        arrayProductos = ArrayList()
        for (i in items.withIndex()){

            //Log.e("esta", "vacio ${arrayProductos}")
            arrayProductos.add("")

        }
    }

      // crear ViewHolder
      var viewHolder: RecyclerView.ViewHolder? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
         val itemView = LayoutInflater.from(context).inflate(R.layout.template_lista_fragmento, parent, false)
        viewHolder = MainViewHolder(itemView)
          return MainViewHolder(itemView)
      }

    override fun getItemCount(): Int {

        return if (datosFirebase.size > 0){
         datosFirebase.size

       } else{
            0
       }
    }
            // Enlazar ViewHolder
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

                val items = datosFirebase[position]
                holder.enlazarVista(items, position)
    }
    inner class MainViewHolder(vista: View): RecyclerView.ViewHolder(vista){

        fun enlazarVista(indumenta: ModeloDeIndumentaria, position: Int){
            Glide.with(context).asBitmap().load(indumenta.imagen).into(itemView.imagenRecycler)
            itemView.tvnombre.text = indumenta.nombre
            itemView.tvprecio.text = indumenta.precio

        }
        var nombre: TextView? = null
        var precio: TextView? = null
        var imagen: ImageView? = null
        init {
            nombre = itemView.findViewById(R.id.tvnombre)
            precio = itemView.findViewById(R.id.tvprecio)
            imagen = itemView.findViewById(R.id.imagenRecycler)
        }
    }

}
    interface OnItemClickListener{
        fun onItemClicked(nombre: ModeloDeIndumentaria){

        }
    }