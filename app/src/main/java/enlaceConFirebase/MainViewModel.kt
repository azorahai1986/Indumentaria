package enlaceConFirebase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import modeloDeDatos.ModeloDeIndumentaria

class MainViewModel: ViewModel() {
    val repo = Repo()

    fun fetchUserData (): LiveData<MutableList<ModeloDeIndumentaria>>{
       val mutableData = MutableLiveData<MutableList<ModeloDeIndumentaria>>()
        repo.getUserData().observeForever { userList ->
        mutableData.value = userList

        }
        return mutableData

    }
}