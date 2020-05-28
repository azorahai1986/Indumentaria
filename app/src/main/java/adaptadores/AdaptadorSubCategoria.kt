package adaptadores

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.indumentaria.R
import fragmentos.FragmentoLista
import kotlinx.android.synthetic.main.template_subcategoria.view.*
import modeloDeDatos.SubCategoria
import java.util.zip.Inflater

class AdaptadorSubCategoria(var mutableListSub: MutableList<SubCategoria>, val activity: FragmentActivity):RecyclerView.Adapter<AdaptadorSubCategoria.ViewHolderSubCategoria>() {



   inner class ViewHolderSubCategoria (itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdaptadorSubCategoria.ViewHolderSubCategoria =
        ViewHolderSubCategoria(LayoutInflater.from(parent.context)
            .inflate(R.layout.template_subcategoria, parent, false))


    override fun getItemCount(): Int = mutableListSub.size


    override fun onBindViewHolder(holder: AdaptadorSubCategoria.ViewHolderSubCategoria, position: Int) {
        val subCat = mutableListSub[position]


        holder.itemView.text_nombre_template_subcategoria.text = subCat.sub
        Glide.with(activity).load(subCat.imagen).into(holder.itemView.image_template_subcategoria)

        //enviaré info a la siguiente lista de modelos
       holder.itemView.card_template_subcategoria.setOnClickListener {
           Toast.makeText(activity, subCat.id, Toast.LENGTH_SHORT).show()
           val fragmentTransaction = activity.supportFragmentManager.beginTransaction()
               fragmentTransaction.replace(R.id.contenedor, FragmentoLista.newInstanceList(subCat.id))
               fragmentTransaction.commit()





        }

        // le daré el click al cardView de subCategorias.........

    }
}