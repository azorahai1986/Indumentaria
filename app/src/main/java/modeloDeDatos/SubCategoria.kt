package modeloDeDatos

data class SubCategoria(var id: String,
                        var cate: String,
                        var sub: String,
                        var imagen: String){
    constructor():this("","","","")
}