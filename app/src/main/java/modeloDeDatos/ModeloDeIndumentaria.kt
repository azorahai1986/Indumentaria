package modeloDeDatos

import java.io.Serializable

class ModeloDeIndumentaria(
    var id: String,
    val cate: String,
    val sub: String,
    val imagen: String,
    val nombre: String,
    val precio: String){
    constructor():this("", "","","","","")
}