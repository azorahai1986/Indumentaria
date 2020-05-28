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
    fun getUserData (idSubCategoria: String): LiveData<MutableList<ModeloDeIndumentaria>>{

         val mutableData =MutableLiveData<MutableList<ModeloDeIndumentaria>>()
         FirebaseFirestore.getInstance().collection("ModeloDeIndumentaria")
             .whereEqualTo("sub", idSubCategoria).get().addOnSuccessListener {

             val listData = mutableListOf<ModeloDeIndumentaria>()

             for (obtenerFireBase in it.documents){
                 val indument = obtenerFireBase.toObject(ModeloDeIndumentaria::class.java)
                 indument?.id = obtenerFireBase.id
                 if (indument != null)

                 listData.add(indument)
                 Log.e("REPO_EXITO_MODELO", listData.toString())

             }
             mutableData.value = listData

         }.addOnFailureListener {
             Log.e("ErrorMODELO", it.toString())
             //Esto lo hice para probar si llega internet a la app.
         }
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
                Log.e("REPO_EXITO_CATEGORIA", it.documents.size.toString())

            }.addOnFailureListener {
                Log.e("ErrorINTERNET", it.toString())
                //Esto lo hice para probar si llega internet a la app.
            }
        return mutableData
    }

    fun getSubCategoria(idCategoria: String): LiveData<MutableList<SubCategoria>>{
        Log.e("REP_IDCATEGORIA", idCategoria.toString())

        val mutableData = MutableLiveData<MutableList<SubCategoria>>()
        FirebaseFirestore.getInstance().collection("Subcatergoria")
            .whereEqualTo("cate", idCategoria)
            .get()
            .addOnSuccessListener {
                val listData = mutableListOf<SubCategoria>()

                //Log.e("REP_LISTDATA_SUB", listData.toString())
                Log.e("idCat",idCategoria)
                for(cat in it.documents){
                    val c = cat.toObject(SubCategoria::class.java)
                    /*Log.e("DENTRO_DEL_FOR_C", c.toString())
                    Log.e("ID", cat.id)
                    Log.e("idCate",c?.cate)
                    if(c!!.cate == idCategoria)
                        Log.e("Comparar", "iguales")
                    else
                        Log.e("Comparar","diferentes")*/
                    c?.id = cat.id

                    if(c!=null)
                        listData.add(c)



                }
                Log.e("DESPUES_DEL_FOR", it.documents.size.toString())

                mutableData.value = listData

            }.addOnFailureListener {
                Log.e("ErrorDatos", it.toString())
                //Esto lo hice para probar si llega internet a la app.

            }


        return mutableData
    }



}