package ar.edu.unsam.algo2

class Ave(val nombre: String) {
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
    fun volar(){ energia = energia - 10 }
    fun comer(cuanto: Int){ energia = energia + (cuanto * 2) }
    fun esFeliz() = energia > ENERGIA_MINIMA
    fun resetEnergia(){ energia = 0 }
    fun saludo() = "Hola soy $nombre"
}
