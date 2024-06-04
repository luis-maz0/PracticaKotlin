package ar.edu.unsam.algo2

import java.time.LocalDate
import java.time.temporal.ChronoUnit

//Requerimientos
/*
1. Proceso se ejecuta ->
       seleccionProyectos()
           elegirProyectos()
           avanzarProceso()
       distribucionDinero()
           distribuirDinero()

2. Interfaz usuario ->
    mostrarProyectosSeleccionados()
    mostrarVariantesDistribucion()
    elegirPropuesta()
    elegirDistribucion()
    ingresarDinero() -> minimo 1000
    confirmarOperacion()

3. Proyectos ->
    DataClass datosProyecto
    tiposProyecto
        sociales
        corporativas
        ecologicas
    impactoSocial() -> polimorficas a todas.

4. Sistema Bancario ->
    m
*/
//1. Seleccion de proyectos
/*Se utiliza el patron composite method */

interface CriterioSeleccionProyecto{
    fun elegirProyectos(proyectos: List<Proyecto>): List<Proyecto>
}
class proyectoMasImpactoSocial : CriterioSeleccionProyecto{
    override fun elegirProyectos(proyectos: List<Proyecto>):List<Proyecto> {
        return proyectos.sortedByDescending { it -> it.impactoSocial() }.take(3)
    }
}

class proyectoMasMenosPlata : CriterioSeleccionProyecto{
    override fun elegirProyectos(proyectos: List<Proyecto>): List<Proyecto> {
        val dineroProyectos = proyectos.sortedBy { it.dineroNecesario }
        return listOf(dineroProyectos.first(), dineroProyectos.last())
    }
}class proyectoNacionales : CriterioSeleccionProyecto{
    override fun elegirProyectos(proyectos: List<Proyecto>):List<Proyecto> {
        return proyectos.filter { it-> it.esOrigenNacional }
    }
}
class proyectoCombinatorio : CriterioSeleccionProyecto{

    override fun elegirProyectos(proyectos: List<Proyecto>):List<Proyecto> {
        
    }
}




//-------------------------------------------------------------
//3. Proyectos
/*En este punto se utiliza el patron template method. */
data class DatosBancarios( val idCuenta: Int)

abstract class Proyecto {
    var nombre = ""
    var descripcion = ""
    var dinero = 1000
    var dineroNecesario = 0
    var datosBancarios = DatosBancarios(idCuenta = 1)
    var responsables = mutableListOf<Persona>()
    var esOrigenNacional = true

    fun impactoSocial() = 0.1 * dinero + inpactoSocialDeterminado()

    abstract fun inpactoSocialDeterminado(): Int
}

data class Persona( val nombre: String, val apellidos: String){

    fun tieneDobleApellido() = apellidos.split(" ").size  > 1
    fun impacto() = if( tieneDobleApellido() ) 45 else 30
}

class ProyectoSocial: Proyecto(){
    val fechaInicio = LocalDate.now()

    override fun inpactoSocialDeterminado(): Int = ChronoUnit.YEARS.between( fechaInicio ,  LocalDate.now()).toInt() * 100
}
class ProyectoCoporativo: Proyecto() {
    val socios = mutableListOf<Persona>()

    override fun inpactoSocialDeterminado(): Int = socios.sumOf { it -> it.impacto() }
}

class ProyectoEcologico: Proyecto(){
    val area = 100

    override fun inpactoSocialDeterminado(): Int = 10 * area
}



