package adaptadores

import actividades.ActividadLista
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.indumentaria.R
import kotlinx.android.synthetic.main.template_categoria.view.*
import modeloDeDatos.Categoria

class AdapterCategoria(var mutableList: MutableList<Categoria>, val activity: FragmentActivity): RecyclerView.Adapter<AdapterCategoria.ViewHolderCategoria>(){

    inner class ViewHolderCategoria(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCategoria = ViewHolderCategoria(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.template_categoria, parent, false)
    )

    override fun getItemCount(): Int = mutableList.size

    override fun onBindViewHolder(holder: ViewHolderCategoria, position: Int) {
        val cat = mutableList[position]
        holder.itemView.text_nombre_template_categoria.text = cat.cate
        Glide.with(activity)
            .load(cat.imagen)
            .into(holder.itemView.image_template_categoria)

       /*  para darle click a una imagen o grid
       holder.itemView.image_template_categoria.setOnClickListener {
            Toast.makeText(activity,"Click imagen", Toast.LENGTH_SHORT).show()
        }*/

        holder.itemView.card_template_categoria.setOnClickListener {
            /**
             * mandando id al otro activity
             */
            Toast.makeText(activity,"Click card${cat.id}", Toast.LENGTH_SHORT).show()
            activity.startActivity(
                Intent(activity, ActividadLista::class.java)
                    /**
                     * putextra
                     */
                    .putExtra(ActividadLista.ID_SUBCATEGORIA, cat.id)
            )

        }
    }
}