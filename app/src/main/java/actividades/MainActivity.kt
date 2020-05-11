package actividades

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.indumentaria.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         // otra forma de darle click a varios boton o cardView que cumplan la misma accion.
        // en este caso van al siguiente activity
        val clickListener: View.OnClickListener = View.OnClickListener { view ->
            when (view.id) {
                R.id.cardViewPromo -> IrAlActivity()
                R.id.cardViewRemeras -> IrAlActivity()
                R.id.cardViewCalsas -> IrAlActivity()
                R.id.cardViewSweaters -> IrAlActivity()
            }
        }
        cardViewPromo.setOnClickListener(clickListener)
        cardViewRemeras.setOnClickListener(clickListener)
        cardViewCalsas.setOnClickListener(clickListener)
        cardViewSweaters.setOnClickListener(clickListener)


    }

    private fun IrAlActivity() {
        val intent = Intent(this, ActividadLista::class.java)
        startActivity(intent)

    }

}
