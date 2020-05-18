package enlaceConFirebase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import modeloDeDatos.Categoria
import modeloDeDatos.ModeloDeIndumentaria
import modeloDeDatos.SubCategoria

class MainViewModel: ViewModel() {
    val repo = Repo()

    fun fetchUserData (): LiveData<MutableList<ModeloDeIndumentaria>>{
       val mutableData = MutableLiveData<MutableList<ModeloDeIndumentaria>>()
        repo.getUserData().observeForever { userList ->
        mutableData.value = userList

        }
        return mutableData

    }

    fun fetchCategoria(): LiveData<MutableList<Categoria>>{
        val mutableData = MutableLiveData<MutableList<Categoria>>()
        repo.getCategoria().observeForever {
            mutableData.value = it

        }
        return mutableData

    }

    /**
     * subcategorias
     */
    fun fetchSubCategoria(idCategoria: String): LiveData<MutableList<SubCategoria>>{
        val mutableData = MutableLiveData<MutableList<SubCategoria>>()
        repo.getSubCategoria(idCategoria).observeForever {
            mutableData.value = it
        }
        return mutableData
    }
}