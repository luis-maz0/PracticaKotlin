package ar.edu.unsam.algo2

open class Ave(val nombre: String) {
    //COMPANION OBJECT: Global para todas las instancias de
    //de la clase.
    companion object{
        //CONSTANTE.
        var ENERGIA_MINIMA = 100
        fun subirEnergiaMinima(cuanto:Int){
            ENERGIA_MINIMA += cuanto
        }
    }

    var energia: Int = 0
    open fun volar(){ energia = energia - 10 }
    fun comer(cuanto: Int){ energia = energia + (cuanto * 2) }
    open fun esFeliz() = energia > ENERGIA_MINIMA
    fun resetEnergia(){ energia = 0 }
    fun saludo() = "Hola soy $nombre"
}

//Herencia. Si quiero heredar de otra clase, es importante que dicha clase sea open.
//Por defecto son cerradas. Idem con los métodos.
class Golondrina(nombre: String) : Ave(nombre) {
    override fun esFeliz() = true
}

class Torcaza(nombre: String): Ave(nombre){
    var vecesVolo = 0
    override fun volar(){
        super.volar()
        vecesVolo++
    }
}
//Estoy probando la creación de una nueva rama!

