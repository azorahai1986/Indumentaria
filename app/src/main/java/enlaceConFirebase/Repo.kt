package enlaceConFirebase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import modeloDeDatos.Categoria
import modeloDeDatos.ModeloDeIndumentaria
import modeloDeDatos.SubCategoria

class Repo {
     // para el recyclerView
    fun getUserData (): LiveData<MutableList<ModeloDeIndumentaria>>{

         val mutableData =MutableLiveData<MutableList<ModeloDeIndumentaria>>()
         FirebaseFirestore.getInstance().collection("ModeloDeIndumentaria").get().addOnSuccessListener { result ->

             val listData = mutableListOf<ModeloDeIndumentaria>()

             for (obtenerFireBase in result){
                 val indument = obtenerFireBase.toObject(ModeloDeIndumentaria::class.java)
                 listData.add(indument)
             }
             mutableData.value = listData
         }/*.addOnFailureListener {
             Log.e("Error", it.toString())
             Esto lo hice para probar si llega internet a la app.
         }*/
         return mutableData
    }

    fun getCategoria(): LiveData<MutableList<Categoria>>{
        val mutableData = MutableLiveData<MutableList<Categoria>>()

        FirebaseFirestore.getInstance().collection("Categoria").get()
            .addOnSuccessListener {
                val listData = mutableListOf<Categoria>()
                for(cat in it.documents){
                    val c = cat.toObject(Categoria::class.java)
                    c?.id = cat.id
                    if(c!=null)
                        listData.add(c)
                }
                mutableData.value = listData
            }
        return mutableData
    }

    fun getSubCategoria(idCategoria: String): LiveData<MutableList<SubCategoria>>{
        val mutableData = MutableLiveData<MutableList<SubCategoria>>()
        FirebaseFirestore.getInstance().collection("Subcategoria")
            .whereEqualTo("cate", idCategoria)
            .get()
            .addOnSuccessListener {
                val listData = mutableListOf<SubCategoria>()
                for(cat in it.documents){
                    val c = cat.toObject(SubCategoria::class.java)
                    c?.id = cat.id
                    if(c!=null)
                        listData.add(c)
                }
                mutableData.value = listData
            }
        return mutableData
    }



}