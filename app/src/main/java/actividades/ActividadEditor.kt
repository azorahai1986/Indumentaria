package actividades

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.indumentaria.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_actividad_editor.*

//aquí crearé la clase providerType
enum class ProviderType {
    BIENVENIDO
}

class ActividadEditor : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividad_editor)

        //Aplico la funcion setup
        val bundle = intent.extras
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")
        setup(email?: "", provider?:"")

    }
    // esta funcion me ayudará a recibir los datos de la autenticacion del usuario
    private fun setup(email: String, provider:String){
        title = "Inicio"
        tvEmail.text = email
        tvPovider.text = provider

        logoutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }
    }
}
