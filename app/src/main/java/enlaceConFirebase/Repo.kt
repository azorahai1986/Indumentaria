package enlaceConFirebase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import modeloDeDatos.ModeloDeIndumentaria

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
         }
         return mutableData

    }
}