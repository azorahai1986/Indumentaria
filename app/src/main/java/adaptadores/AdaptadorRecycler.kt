package adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.indumentaria.R
import kotlinx.android.synthetic.main.template_lista_fragmento.view.*
import modeloDeDatos.ModeloDeIndumentaria

class AdaptadorRecycler(var datosFirebase: ArrayList<ModeloDeIndumentaria>, private val context: Context): RecyclerView.Adapter<AdaptadorRecycler.MainViewHolder>() {


      // crear ViewHolder
      var viewHolder: RecyclerView.ViewHolder? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
         val view = LayoutInflater.from(context).inflate(R.layout.template_lista_fragmento, parent, false)
        viewHolder = MainViewHolder(view)
          return MainViewHolder(view)
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
            itemView.nombre.text = indumenta.nombre
            itemView.precio.text = indumenta.precio

        }
    }

}
    interface OnItemClickListener{
        fun onItemClicked(nombre: ModeloDeIndumentaria){

        }
    }