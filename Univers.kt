//----------------------------------------------
//
//      Univers.kt
//
//----------------------------------------------
//
//      Copyright B. Froger 2020
//
//----------------------------------------------
package Monde.Univers

open class Univers{

    lateinit var nomUnivers:String

    open fun display(){
        println("+=====================+")
        println("|   objet Univers     |")
        println("+---------------------+")
        println("| nom    | %10s |".format(this.nomUnivers))
        println("+=====================+")
    }
    
}