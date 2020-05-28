package adaptadores

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.indumentaria.R
import kotlinx.android.synthetic.main.template_lista_fragmento.view.*
import modeloDeDatos.ModeloDeIndumentaria

class AdaptadorRecycler(var mutableListModel: ArrayList<ModeloDeIndumentaria>, val activity:FragmentActivity): RecyclerView.Adapter<AdaptadorRecycler.ViewHolderModel>() {

    inner class ViewHolderModel (itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdaptadorRecycler.ViewHolderModel =
        ViewHolderModel(LayoutInflater.from(parent.context)
            .inflate(R.layout.template_lista_fragmento, parent, false))

    override fun getItemCount(): Int = mutableListModel.size
            // Enlazar ViewHolder
    override fun onBindViewHolder(holder: AdaptadorRecycler.ViewHolderModel, position: Int) {
                val modelosFb = mutableListModel[position]
                holder.itemView.nombre_template_model.text = modelosFb.nombre
                holder.itemView.precio_template_model.text = modelosFb.nombre
                holder.itemView.categorias_template_model.text = modelosFb.nombre
                holder.itemView.subCate_template_model.text = modelosFb.nombre
                Glide.with(activity).load(modelosFb.imagen).into(holder.itemView.imagen_template_modelo)


            }

}
