package actividades

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.example.indumentaria.R
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_actividad_registrarse.*

class ActividadRegistrarse : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividad_registrarse)




        setup()
    }

    private fun setup(){
        title = "Autenticar"

        btRegistrarse?.setOnClickListener {
            if (etEmail.text.isNotEmpty() && etPassword.text.isNotEmpty()){

                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(etEmail.text.toString(), etPassword.text.toString())
                    .addOnCompleteListener {
                        if (it.isSuccessful){
                            showHome(it.result?.user?.email?: "", ProviderType.BIENVENIDO)

                        }else{
                            showAlert()

                        }
                    }
            }
        }
        btAcceder?.setOnClickListener {
            if (etEmail.text.isNotEmpty() && etPassword.text.isNotEmpty()){

                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(etEmail.text.toString(), etPassword.text.toString())
                    .addOnCompleteListener {
                        if (it.isSuccessful){
                            showHome(it.result?.user?.email?: "", ProviderType.BIENVENIDO)

                        }else{
                            showAlert()

                        }
                    }
            }
        }
    }
    //crearé una alerta
    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage(" Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog:AlertDialog = builder.create()
        dialog.show()
    }
    // funcion para pasar los datos del email y la contraseña
                                       // este providerType será creado en el activityEditor
    private fun showHome(email: String, provider: ProviderType){
        val homeIntent = Intent(this, ActividadEditor::class.java)
        homeIntent.putExtra("email", email)
        homeIntent.putExtra("provider", provider.name)
        startActivity(homeIntent)

    }
}
