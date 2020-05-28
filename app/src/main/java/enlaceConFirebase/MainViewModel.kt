package enlaceConFirebase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import modeloDeDatos.Categoria
import modeloDeDatos.ModeloDeIndumentaria
import modeloDeDatos.SubCategoria

class MainViewModel: ViewModel() {
    val repo = Repo()

    fun fetchUserData (idSubCategoria: String): LiveData<MutableList<ModeloDeIndumentaria>>{
       val mutableData = MutableLiveData<MutableList<ModeloDeIndumentaria>>()
        repo.getUserData(idSubCategoria).observeForever {
        mutableData.value = it

        }
        return mutableData

    }

    fun fetchCategoria(): LiveData<MutableList<Categoria>>{
        val mutableData = MutableLiveData<MutableList<Categoria>>()
        repo.getCategoria().observeForever {
            mutableData.value = it
            Log.e("ViemodelMeCategoria", it.toString())

        }
        return mutableData

    }

    /**
     * subcategorias
     */
    fun fetchSubCategoria(idCategoria: String?): LiveData<MutableList<SubCategoria>>{
        val mutableData = MutableLiveData<MutableList<SubCategoria>>()
        repo.getSubCategoria(idCategoria!!).observeForever {
            mutableData.value = it
            Log.e("ViemodelMessageSub", it.toString())
        }
        return mutableData

    }
}